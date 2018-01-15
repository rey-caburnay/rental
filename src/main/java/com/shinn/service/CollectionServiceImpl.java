package com.shinn.service;

import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.shinn.dao.factory.ResultStatus;
import com.shinn.dao.repos.ApartmentDao;
import com.shinn.dao.repos.CollectionDao;
import com.shinn.dao.repos.ElectricBillDao;
import com.shinn.dao.repos.ElectricCollectionDao;
import com.shinn.dao.repos.RentalDao;
import com.shinn.dao.repos.RenterDao;
import com.shinn.dao.repos.RoomDao;
import com.shinn.service.model.Apartment;
import com.shinn.service.model.Collection;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.ElectricCollection;
import com.shinn.service.model.Renter;
import com.shinn.service.model.Room;
import com.shinn.service.model.Transaction;
import com.shinn.ui.model.BillingForm;
import com.shinn.ui.model.Cash;
import com.shinn.ui.model.ElectricCollectionForm;
import com.shinn.ui.model.Response;
import com.shinn.util.DateUtil;
import com.shinn.util.PdfUtil;
import com.shinn.util.RentStatus;
import com.shinn.util.StringUtil;

@Service
public class CollectionServiceImpl implements CollectionService {
  private static final org.slf4j.Logger LOGGER =
      LoggerFactory.getLogger(CollectionServiceImpl.class);
   
  
  @Autowired
  CollectionDao collectionDao;
  @Autowired
  ElectricCollectionDao electricCollectionDao;
  @Autowired
  ElectricBillDao electricBillDao;
  @Autowired
  RentalDao rentalDao;
  @Autowired
  RenterDao renterDao;
  @Autowired
  ApartmentDao apartmentDao;
  @Autowired
  RoomDao roomDao;

  @Override
  public Response<ElectricCollectionForm> saveElectricCollection(ElectricCollectionForm form)
      throws Exception {
    Response<ElectricCollectionForm> resp = new Response<>();

    ElectricCollection electricCollection = new ElectricCollection();
    electricCollection.setAmount(Double.parseDouble(form.getCash().getAmountPaid()));
    electricCollection.setBillingNo(form.getBilling().getBillingNo());
    electricCollection.setCollectionDate(DateUtil.getCurrentDate());
    electricCollection.setDueDate(form.getBilling().getDueDate());
    if (Double.parseDouble(form.getCash().getBalance()) > 0) {
      electricCollection.setOverdue(Double.parseDouble(form.getCash().getBalance()));
    } else {
      electricCollection.setOverdue(Double.parseDouble("-" + form.getCash().getDeposit()));
    }

    electricCollection.setStatus(RentStatus.PAID);
    electricCollection.setCashChange(Double.parseDouble(form.getCash().getCashChange()));
    electricCollection.setCashReceived(Double.parseDouble(form.getCash().getCashReceived()));
    int id = electricCollectionDao.saveUpdate(electricCollection);
    electricCollection.setId(id);
    form.setCollection(electricCollection);
    // update electric bill info
    // ElectricBill electricBill = electricBillDao.getByAptRoom(form.getAptId(), form.getRoomId());
    // electricBill.setPayment(Double.parseDouble(form.getCash().getAmountPaid()));
    // electricBill.setPreviousReading(form.getBilling().getCurrentReading());
    // electricBill.setCurrentReading(null);
    // electricBill.setAmount(0d);
    // electricBill.setStatus(RentStatus.PAID);

    // electricBill.setDueDate(DateUtil.addDays(new Date(), days)); //due date is for surcharge
    // computation
    resp.setModel(form);
    resp.setResponseStatus(ResultStatus.RESULT_OK);
    electricCollectionDao.commit();

    return resp;
  }

  @Override
  public Response<BillingForm> createCollection(BillingForm form) {

    if (form.getBills() != null && !form.getBills().isEmpty()) {
      return createPropertyCollection(form);
    } else {
      return createElectricCollection(form);
    }
//    return null;
  }
  
  private Response<BillingForm> createElectricCollection(BillingForm form) {
    Response<BillingForm> resp = new Response<>();
    try {
      for (ElectricBill electric : form.getRooms()) {
        Collection collection = new Collection();
        String collectionNo = StringUtil.generateBillingNo(electric.getAptId(), electric.getRoomId());
        Renter renter = renterDao.getOccupancy(electric.getAptId(), 
            electric.getRoomId());
        collection.setAptId(electric.getAptId());
        collection.setRoomId(electric.getRoomId());
        collection.setBalance(electric.getOverdue());
        collection.setAmountPaid(Double.valueOf(form.getCash().getAmountPaid()));
        collection.setCashReceived(Double.valueOf(form.getCash().getCashReceived()));
        collection.setCashChange(Double.valueOf(form.getCash().getCashChange()));
        collection.setDeposit(Double.valueOf(form.getCash().getDeposit()));
        collection.setTxDate(DateUtil.getCurrentDate());
        collection.setStatus(RentStatus.PAID);
        collection.setBillingNo(electric.getBillingNo());
        collection.setTxId(electric.getId());
        collection.setUserId(1);
        collection.setRenterId(renter.getId());
        collection.setBillingNo(electric.getBillingNo());
        collection.setCollectionNo(collectionNo);
        collectionDao.saveUpdate(collection);
        //update mst_electric
        Double newAmount = Double.parseDouble(form.getCash().getAmountPaid()) - electric.getAmount();
        electric.setAmount(newAmount);
        electric.setLastBillNo(electric.getBillingNo());
        electric.setBillingNo(null);
        electric.setCurrentReading(0);
        electric.setGenerationDate(DateUtil.getCurrentDate());
        electric.setReadingDate(form.getReadingDate());
        electric.setCollectionNo(collectionNo);
        collectionDao.saveUpdate(collection);
        electricBillDao.saveUpdate(electric);
        collectionDao.commit();
        electricBillDao.commit();
        resp.setModel(form);
        resp.setResponseStatus(ResultStatus.RESULT_OK);
      }
    } catch (Exception e) {
      resp.setErrorMsg(e.getMessage());
      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
    }
    return resp;
  }

  private Response<BillingForm> createPropertyCollection(BillingForm form) {
    Response<BillingForm> response = new Response<>();
    try {
      for (Transaction tx : form.getBills()) {
        Collection collection = new Collection();
        String collectionNo = StringUtil.generateBillingNo(tx.getAptId(), tx.getRoomId());
        collection.setAptId(tx.getAptId());
        collection.setRoomId(tx.getRoomId());
        collection.setRenterId(tx.getRenterId());
        collection.setBalance(tx.getBalance());
        LOGGER.info("amount paid:{}", Double.valueOf(form.getCash().getAmountPaid()));
        collection.setAmountPaid(Double.valueOf(form.getCash().getAmountPaid()));
        collection.setCashReceived(Double.valueOf(form.getCash().getCashReceived()));
        collection.setCashChange(Double.valueOf(form.getCash().getCashChange()));
        collection.setCurrentRoomRate(tx.getRoom().getRate());
        collection.setDeposit(Double.valueOf(form.getCash().getDeposit()));
        collection.setTxDate(DateUtil.getCurrentDate());
        collection.setStatus(RentStatus.PAID);
        collection.setBillingNo(tx.getBillingNo());
        collection.setTxId(tx.getId());
        collection.setUserId(1);
        collection.setBillingNo(form.getBillingNo());
        collection.setCollectionNo(collectionNo);
        collectionDao.saveUpdate(collection);
        // Double newAmount = tx.getAmount() - Double.valueOf(form.getCash().getAmountPaid());
        // update the transaction after creating the collection
        // amount reset to current room rate
        tx.setAmount(tx.getRoom().getRate());
        tx.setAmountPaid(Double.valueOf(form.getCash().getAmountPaid()));
        // balance amount - amount paid
        tx.setBalance(Double.valueOf(form.getCash().getBalance()));
        tx.setDeposit(Double.valueOf(form.getCash().getDeposit()));
        tx.setTxType("Cash");
        tx.setUpdatedDate(DateUtil.getCurrentDate());
        tx.setUpdtCnt(tx.getUpdtCnt() + 1);
        tx.setCollectionNo(collectionNo);
        tx.setBillingNo(form.getBillingNo());
        
        rentalDao.saveUpdate(tx);
        collectionDao.commit();
        
      }
      response.setModel(form);
      response.setResponseStatus(ResultStatus.RESULT_OK);

    } catch (Exception e) {
      LOGGER.error("createPropertyCollection {}", e.getMessage());
      response.setErrorMsg(e.getMessage());
      response.setResponseStatus(ResultStatus.SAVE_FAILED);
    }

    return response;
  }

  @Override
  public String createPdf(BillingForm form) {
    String generatedFile = form.getBillingNo() + ".pdf";
    File file = new File(generatedFile);
    try {

      Document document = PdfUtil.createDocument(file.getAbsolutePath());
      switch (form.getBillingType()) {
        case RentStatus.BILL_ELECTRIC:
          for (ElectricBill electricBill : form.getRooms()) {
            PdfPTable billTable =
                createPaymentReceipt(electricBill, form.getCash());
            document.add(billTable);
            document.newPage();
          }
          break;
        case RentStatus.BILL_RENT:
          for (Transaction tx : form.getBills()) {
            PdfPTable billTable = createPaymentReceipt(tx);
            document.add(billTable);
            DottedLineSeparator separator = new DottedLineSeparator();
            separator.setPercentage(59500f / 523f);
            Chunk linebreak = new Chunk(separator);
            document.add(linebreak);
          }
          break;
        default:
          // water
      }

      document.close();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    LOGGER.info("generated file {}", generatedFile);
    return generatedFile;
  }
  
  private PdfPTable createPaymentReceipt(Transaction tx) {

    PdfPTable table = new PdfPTable(2);
    Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    Phrase p = new Phrase("Caburnay Apartmentz");
    Font f = new Font();
    f.setFamily("Helvicta");
    f.setSize(20f);
    f.setStyle(Font.BOLD);
    p.setFont(FontFactory.getFont(FontFactory.HELVETICA));
    PdfPCell cell = new PdfPCell(p);

    cell.setBorder(Rectangle.NO_BORDER);
    cell.setFixedHeight(30f);
    cell.setColspan(2);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(
        PdfUtil.createCell("Rent", Element.ALIGN_LEFT, Rectangle.NO_BORDER,1, boldFont));

    // cell.addElement(new PdfPCell());;
    table.addCell(cell);
    table.getDefaultCell().setFixedHeight(30);
    table.addCell(
        PdfUtil.createCell("Ref #: " + tx.getCollectionNo(), Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell(
        "Date : " + DateUtil.formatDate(DateUtil.getCurrentDate(), DateUtil.YYYYMMDD_DASH),
        Element.ALIGN_LEFT, Rectangle.NO_BORDER));

    // table.getDefaultCell().setFixedHeight(20);
    table.addCell(PdfUtil.createCell("Customer Name:", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(
        PdfUtil.createCell(tx.getRenter().getFullName(), Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell("Apartment Name:", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell(tx.getAptName(), Element.ALIGN_LEFT, Rectangle.NO_BORDER));

    table.addCell(PdfUtil.createCell("Room # / Name :", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell(tx.getRoom().getRoomNo() + " / " + tx.getRoom().getRoomDesc(),
        Element.ALIGN_LEFT, Rectangle.NO_BORDER));

    table.addCell(PdfUtil.createCell("Amount Paid:", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell(StringUtil.toCurrency(tx.getAmountPaid()), Element.ALIGN_LEFT,
        Rectangle.NO_BORDER));

   
    PdfPCell spacer = new PdfPCell();
    spacer.setFixedHeight(30f);
    spacer.setColspan(2);
    spacer.setBorder(Rectangle.NO_BORDER);
    table.addCell(spacer);
    return table;
  }
  
  private PdfPTable createPaymentReceipt(ElectricBill tx, Cash cash) throws Exception {
    Apartment apartment = apartmentDao.getById(tx.getAptId());
    Room room = roomDao.getById(tx.getRoomId());
    PdfPTable table = new PdfPTable(2);
    Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    Phrase p = new Phrase("Caburnay Apartmentz");
    Font f = new Font();
    f.setFamily("Helvicta");
    f.setSize(20f);
    f.setStyle(Font.BOLD);
    p.setFont(FontFactory.getFont(FontFactory.HELVETICA));
    PdfPCell cell = new PdfPCell(p);

    cell.setBorder(Rectangle.NO_BORDER);
    cell.setFixedHeight(30f);
    cell.setColspan(2);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(
        PdfUtil.createCell("Electric", Element.ALIGN_LEFT, Rectangle.NO_BORDER,1, boldFont));

    // cell.addElement(new PdfPCell());;
    table.addCell(cell);
    table.getDefaultCell().setFixedHeight(30);
    table.addCell(
        PdfUtil.createCell("Ref #: " + tx.getCollectionNo(), Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell(
        "Date : " + DateUtil.formatDate(DateUtil.getCurrentDate(), DateUtil.YYYYMMDD_DASH),
        Element.ALIGN_LEFT, Rectangle.NO_BORDER));

    // table.getDefaultCell().setFixedHeight(20);
   
    table.addCell(PdfUtil.createCell("Apartment Name:", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell(apartment.getName(), Element.ALIGN_LEFT, Rectangle.NO_BORDER));

    table.addCell(PdfUtil.createCell("Room # / Name :", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell(room.getRoomNo() + " / " + room.getRoomName() + " " + room.getRoomDesc(),
        Element.ALIGN_LEFT, Rectangle.NO_BORDER));

    table.addCell(PdfUtil.createCell("Amount Paid:", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell(StringUtil.toCurrency(Double.parseDouble(cash.getAmountPaid())), Element.ALIGN_LEFT,
        Rectangle.NO_BORDER));

   
    PdfPCell spacer = new PdfPCell();
    spacer.setFixedHeight(30f);
    spacer.setColspan(2);
    spacer.setBorder(Rectangle.NO_BORDER);
    table.addCell(spacer);
    return table;
  }


}
