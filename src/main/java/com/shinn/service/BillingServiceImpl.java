package com.shinn.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.shinn.dao.repos.BillingDao;
import com.shinn.dao.repos.ElectricBillDao;
import com.shinn.dao.repos.ElectricProviderDao;
import com.shinn.dao.repos.RentalDao;
import com.shinn.dao.repos.RenterDao;
import com.shinn.dao.repos.RoomDao;
import com.shinn.service.model.Billing;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.ElectricProvider;
import com.shinn.service.model.Renter;
import com.shinn.service.model.Room;
import com.shinn.service.model.Transaction;
import com.shinn.ui.model.BillingForm;
import com.shinn.ui.model.Response;
import com.shinn.ui.model.Result;
import com.shinn.util.DateUtil;
import com.shinn.util.PdfUtil;
import com.shinn.util.RentStatus;
import com.shinn.util.StringUtil;

@Service
public class BillingServiceImpl implements BillingService {
  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BillingServiceImpl.class);
  private static String PDF_PATH = "C:\\Users\\rbkshinn";
  private static Integer NUMBER_OF_ROWS = 3;
  private static final Integer MINIMUM_DAYS_TO_GENERATE = 20;
  private static final Integer DAYS_FOR_SURCHARGE = 7;

  @Autowired
  ElectricBillDao electricBillDao;
  @Autowired
  ElectricProviderDao electricProviderDao;
  @Autowired
  BillingDao billingDao;
  @Autowired
  RentalDao rentalDao;
  @Autowired
  RoomDao roomDao;
  @Autowired
  RenterDao renterDao;

  /**
   * 
   */
  public Response<Billing> getBilling(Integer aptId, Integer roomId, String billType) {
    Response<Billing> resp = new Response<Billing>();
    Billing bill = billingDao.getLatestBilling(aptId, roomId, billType);
    if (bill != null) {
      resp.setModel(bill);
      resp.setResponseStatus(ResultStatus.RESULT_OK);
    } else {
      resp.setErrorMsg("Billing is null");
      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
    }
    return resp;
  }

  /**
   * 
   */
  @Override
  public Response<BillingForm> generateBillings(BillingForm billingForm) {
    Response<BillingForm> resp = new Response<BillingForm>();
    switch (billingForm.getBillingType()) {
      case RentStatus.BILL_ELECTRIC:
        billingForm.setRooms(this.generateElectricBilling(billingForm.getRooms()));
        break;
      case RentStatus.BILL_RENT:
        resp = this.createRoomBilling(billingForm);
      default:

    }
    if (billingForm.getRooms() != null && billingForm.getRooms().size() > 0) {
      // this.generateElectricBilling(billingForm.getRooms());
    }

    // // TODO save bill;
    // // save billings
    // Billing billing = null;
    // String billingNo = null;
    // int numberOfDays = 0;
    // try {
    // for (ElectricBill electricBill : billingForm.getRooms()) {
    // // create only 1 billing within a month
    // // billingDao.get
    // billing = billingDao.getLatestBilling(electricBill.getAptId(), electricBill.getRoomId());
    // if (billing != null) {
    // numberOfDays = DateUtil.daysBetween(billing.getGenerationDate(), DateUtil.getCurrentDate());
    // }
    //
    // // if (billing == null || numberOfDays >= MINIMUM_DAYS_TO_GENERATE)
    //
    // billing = new Billing();
    // billing.setAmount(electricBill.getAmount());
    // billing.setOverdue(electricBill.getOverdue());
    // billing.setAptId(electricBill.getAptId());
    // billing.setRoomId(electricBill.getRoomId());
    // billing.setBillType(ELECTRIC_BILL);
    // billing.setCurrentReading(electricBill.getCurrentReading());
    // billing.setPreviousReading(electricBill.getPreviousReading());
    // billing.setDueDate(electricBill.getDueDate());
    // billing.setGenerationDate(DateUtil.getCurrentDate());
    // billing.setReadingDate(billingForm.getReadingDate());
    // billing.setDiffReading(electricBill.getDiffReading());
    // Long dateTime = DateUtil.getCurrentDate().getTime();
    // billingNo = String.valueOf(dateTime) + String.valueOf(electricBill.getAptId())
    // + String.valueOf(electricBill.getRoomId());
    // billing.setBillingNo(billingNo);
    // billingDao.saveUpdate(billing);
    // electricBill.setReadingDate(billingForm.getReadingDate());
    // electricBill.setDueDate(DateUtil.addDays(DateUtil.getCurrentDate(), 5));
    // electricBill.setLastBillNo(billingNo);
    // electricBill.setBillingNo(billingNo);
    // electricBill.setPreviousReading(billing.getCurrentReading());
    // electricBill.setCurrentReading(0);
    // electricBill.setTotalAmount(billingForm.getTotalAmount());
    // electricBill.setDueDate(DateUtil.addDays(DateUtil.getCurrentDate(), DAYS_FOR_SURCHARGE));
    // electricBillDao.saveUpdate(electricBill);
    // }
    // billingDao.commit();
    // billingForm.setBillingNo(billingNo);
    // resp.setModel(billingForm);
    // }catch (Exception e) {
    // logger.error("generateBilling:", e);
    // resp.setErrorMsg(e.getMessage());
    // resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
    // }
    return resp;
  }

  /**
   * return pdf file location;
   */
  public String createPdf(BillingForm billingForm) {
    String generatedFile = PDF_PATH + "/" + billingForm.getBillingNo() + ".pdf";
    try {

      Document document = PdfUtil.createDocument(generatedFile);
      switch (billingForm.getBillingType()) {
        case RentStatus.BILL_ELECTRIC:
          for (ElectricBill electricBill : billingForm.getRooms()) {
            PdfPTable billTable =
                createElectricBill(electricBill, billingForm.getElectricProvider());
            document.add(billTable);
            document.newPage();
          }
          break;
        case RentStatus.BILL_RENT:
          for (Transaction tx : billingForm.getBills()) {
            PdfPTable billTable = createRentReceipt(tx);
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
      // for (ElectricBill electricBill : billingForm.getRooms()) {
      // PdfPTable billTable = createElectricBill(electricBill, billingForm.getElectricProvider());
      // document.add(billTable);
      // document.newPage();
      // }
      document.close();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return generatedFile;
  }


  /**
   * format a bill to a pdf table
   * 
   * @param bill
   * @param provider
   * @return
   */
  public PdfPTable createElectricBill(ElectricBill bill, ElectricProvider provider) {
    PdfPTable table = new PdfPTable(NUMBER_OF_ROWS);

    String customerInfo = "Billing #:" + bill.getLastBillNo() + "Room #: " + bill.getRoomId() + "\t"
        + "Meter #: " + bill.getMeterNo();
    table.addCell(PdfUtil.createCell(customerInfo, Element.ALIGN_CENTER, Rectangle.NO_BORDER,
        NUMBER_OF_ROWS));
    // Rate
    table.addCell(
        PdfUtil.createCell("RATE", Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS));

    table.addCell(PdfUtil.createCell("Generation Charge"));
    table.addCell(PdfUtil.createCell(provider.getGenerationCharge() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getGenerationCharge())));

    table.addCell(PdfUtil.createCell("Transmission Charge"));
    table.addCell(PdfUtil.createCell(provider.getTransmissionCharge() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getTransmissionCharge())));

    table.addCell(PdfUtil.createCell("System Loss Charge"));
    table.addCell(PdfUtil.createCell(provider.getSystemLoss() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getSystemLossCharge())));

    // subtotal here
    table.addCell(PdfUtil.createCell("Subtotal 1", Element.ALIGN_LEFT, Rectangle.NO_BORDER,
        NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getSubTotal1()),
        Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));

    // distriution revenues
    table.addCell(PdfUtil.createCell("Distribution Revenues", Element.ALIGN_LEFT,
        Rectangle.NO_BORDER, NUMBER_OF_ROWS, PdfUtil.DEFAULT_FONT_BOLD));

    table.addCell(PdfUtil.createCell("Distribution Charge"));
    table.addCell(PdfUtil.createCell(provider.getDistributionCharge() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getDistributionCharge())));

    table.addCell(PdfUtil.createCell("Supply Charge"));
    table.addCell(PdfUtil.createCell(provider.getSupplyCharge() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getSupplyCharge())));

    table.addCell(PdfUtil.createCell("Retail Customer Charge"));
    table.addCell(PdfUtil.createCell(provider.getRetailCustomer() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getCustomerCharge())));

    table.addCell(PdfUtil.createCell("Metering System Charge"));
    table.addCell(PdfUtil.createCell(provider.getMeteringSystem() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getMeteringCharge())));

    // subtotal 2 here
    table.addCell(PdfUtil.createCell("Subtotal 2", Element.ALIGN_LEFT, Rectangle.NO_BORDER,
        NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getSubTotal2()),
        Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));

    // for MECO need to confirm othes bill since its negative.
    // for VECO multiply
    // other, life line discount
    table.addCell(PdfUtil.createCell("Others", Element.ALIGN_LEFT, Rectangle.NO_BORDER,
        NUMBER_OF_ROWS, PdfUtil.DEFAULT_FONT_BOLD));

    table.addCell(PdfUtil.createCell("Lifeline Discount"));
    table.addCell(PdfUtil.createCell(provider.getTransmissionCharge() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getTransmissionCharge())));

    if (bill.getSurcharge() != null) {
      table.addCell(PdfUtil.createCell("Surcharge"));
      table.addCell(PdfUtil.createCell(provider.getSurcharge() + "/kwh"));
      table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getSurcharge())));
    }

    // subtotal 3
    table.addCell(PdfUtil.createCell("Subtotal 3", Element.ALIGN_LEFT, Rectangle.NO_BORDER,
        NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getSubTotal3()),
        Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));

    // government revenues
    table.addCell(PdfUtil.createCell("Government Revenues", Element.ALIGN_LEFT, Rectangle.NO_BORDER,
        NUMBER_OF_ROWS));

    table.addCell(PdfUtil.createCell("Local Franchise Tax"));
    table.addCell(PdfUtil.createCell(provider.getLocalFranchiseTax() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getTax().getLocalFranchise())));

    table.addCell(PdfUtil.createCell("Generation/Transmission"));
    table.addCell(PdfUtil.createCell(provider.getGenerationTax() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getTax().getGeneration())));

    table.addCell(PdfUtil.createCell("Distribution"));
    table.addCell(PdfUtil.createCell(provider.getDistributionTax() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getTax().getDistribution())));

    table.addCell(PdfUtil.createCell("Others"));
    table.addCell(PdfUtil.createCell(provider.getOthersTax() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getTax().getOthers())));

    table.addCell(PdfUtil.createCell("NPC Standard Cost"));
    table.addCell(PdfUtil.createCell(provider.getNpc() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getTax().getNpc())));

    table.addCell(PdfUtil.createCell("Missionary Electrification"));
    table.addCell(PdfUtil.createCell(provider.getMissionaryElectrification() + "/kwh"));
    table.addCell(
        PdfUtil.createCell(String.valueOf(bill.getBill().getTax().getMissionaryElectrification())));

    table.addCell(PdfUtil.createCell("Environmental Charge"));
    table.addCell(PdfUtil.createCell(provider.getEnvironmentalCharge() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getTax().getEnvironmental())));

    table.addCell(PdfUtil.createCell("Fit All Renewable"));
    table.addCell(PdfUtil.createCell(provider.getFitAllRenewable() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getTax().getFitAllRenewable())));

    // subtotal 4
    table.addCell(PdfUtil.createCell("Subtotal 4", Element.ALIGN_LEFT, Rectangle.NO_BORDER,
        NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getSubTotal3()),
        Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));

    // gross amount
    table.addCell(PdfUtil.createCell("Gross Amount", Element.ALIGN_LEFT, Rectangle.NO_BORDER,
        NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getGrossAmount())));

    // current amount
    table.addCell(PdfUtil.createCell("Current Amount", Element.ALIGN_LEFT, Rectangle.NO_BORDER,
        NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getCurrentAmount())));

    table.addCell(PdfUtil.createCell("Overdue", Element.ALIGN_LEFT, Rectangle.NO_BORDER,
        NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getOverdue())));

    // total amount
    table.addCell(PdfUtil.createCell("Total Amount Due", Element.ALIGN_LEFT, Rectangle.NO_BORDER,
        NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getGrossAmount())));

    // disconnection Notice
    table.addCell(PdfUtil.createCell("Disconnection Due Date: 48 hours from receipt hereof",
        Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS, PdfUtil.DEFAULT_FONT_BOLD));

    // last payment
    // query from collections //TODO
    table.addCell(PdfUtil.createCell("Last Payment", Element.ALIGN_LEFT, Rectangle.NO_BORDER,
        NUMBER_OF_ROWS, PdfUtil.DEFAULT_FONT));

    // spacing for the nex bill
    for (int i = 0; i < NUMBER_OF_ROWS; i++) {
      table.addCell(PdfUtil.createCell("", Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS,
          PdfUtil.DEFAULT_FONT));
    }
    return table;
  }

  /**
  *   
  */
  @Override
  public Response<ElectricBill> getElectricBills(Integer aptId) {
    Response<ElectricBill> resp = new Response<ElectricBill>();
    try {
      java.util.List<ElectricBill> electricBills = electricBillDao.getElectricBillByApt(aptId);
      Iterator<ElectricBill> itr = electricBills.iterator();
      while (itr.hasNext()) {
        ElectricBill electricBill = itr.next();
        electricBill
            .setElectricProvider(electricProviderDao.getProvider(electricBill.getProvider()));
      }
      resp.setResult(electricBills);
      resp.setResponseStatus(ResultStatus.RESULT_OK);
    } catch (Exception e) {
      resp.setErrorMsg(e.getMessage());
      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
    }
    return resp;

  }

  @Override
  public byte[] getPdfContent(String pdfLocation) throws Exception {
    Path path = Paths.get(pdfLocation);
    byte[] pdfContents = null;
    try {
      pdfContents = Files.readAllBytes(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return pdfContents;
  }

  private List<ElectricBill> generateElectricBilling(List<ElectricBill> billings) {
    // TODO save bill;
    // save billings
    Billing billing = null;
    String billingNo = null;
    int numberOfDays = 0;
    try {
      for (ElectricBill electricBill : billings) {
        // create only 1 billing within a month
        billing = billingDao.getLatestBilling(electricBill.getAptId(), electricBill.getRoomId(),
            RentStatus.BILL_ELECTRIC);
        if (billing != null) {
          numberOfDays =
              DateUtil.daysBetween(billing.getGenerationDate(), DateUtil.getCurrentDate());
        }

        // if (billing == null || numberOfDays >= MINIMUM_DAYS_TO_GENERATE)

        billing = new Billing();
        billing.setAmount(electricBill.getAmount());
        billing.setOverdue(electricBill.getOverdue());
        billing.setAptId(electricBill.getAptId());
        billing.setRoomId(electricBill.getRoomId());
        billing.setBillType(RentStatus.BILL_ELECTRIC);
        billing.setCurrentReading(electricBill.getCurrentReading());
        billing.setPreviousReading(electricBill.getPreviousReading());
        billing.setDueDate(electricBill.getDueDate());
        billing.setGenerationDate(DateUtil.getCurrentDate());
        billing.setReadingDate(electricBill.getReadingDate());
        billing.setDiffReading(electricBill.getDiffReading());
        Long dateTime = DateUtil.getCurrentDate().getTime();
        billingNo = String.valueOf(dateTime) + String.valueOf(electricBill.getAptId())
            + String.valueOf(electricBill.getRoomId());
        billing.setBillingNo(billingNo);
        billingDao.saveUpdate(billing);
        electricBill.setDueDate(DateUtil.addDays(DateUtil.getCurrentDate(), 5));
        electricBill.setLastBillNo(billingNo);
        electricBill.setBillingNo(billingNo);
        electricBill.setPreviousReading(billing.getCurrentReading());
        electricBill.setCurrentReading(0);
        electricBill.setTotalAmount(electricBill.getTotalAmount());
        electricBill.setDueDate(DateUtil.addDays(DateUtil.getCurrentDate(), DAYS_FOR_SURCHARGE));
        electricBillDao.saveUpdate(electricBill);
      }
      billingDao.commit();

    } catch (Exception e) {
      logger.error("generateElectricBilling:", e);

    }
    return billings;
  }

  @Override
  public Response<Transaction> getRoomBilling(Integer aptId, Integer roomId) {
    Response<Transaction> response = new Response<>();
    List<Transaction> rentals = rentalDao.getTransactionByApt(aptId, roomId);
    try {
      for (Transaction transaction : rentals) {
        Room room = roomDao.getRoom(transaction.getAptId(), transaction.getRoomId());

        Renter renter = renterDao.getById(transaction.getRenterId());
        transaction.setRenter(renter);

        Date txDate = transaction.getUpdatedDate();
        if (txDate == null) {
          txDate = transaction.getTxDate();
        }
        int unpaidBill = DateUtil.daysBetween(txDate, DateUtil.getCurrentDate());
        unpaidBill = unpaidBill / DateUtil.THIRTYDAYS;
        if (unpaidBill < 1) {
          unpaidBill = 1;
        }
        transaction.setUnpaidBill(unpaidBill);

        Double amount = room.getRate() * unpaidBill;
        if (transaction.getAmount() != null) {
          amount = amount + transaction.getAmount();
        }
        transaction.setAmount(amount);
        transaction.setAmountPayable(amount + transaction.getBalance());

        logger.info("room:" + room);
        transaction.setRoom(room);
      }
      response.setResult(rentals);
      response.setResponseStatus(ResultStatus.RESULT_OK);
    } catch (Exception e) {
      response.setResponseStatus(ResultStatus.GENERAL_ERROR);
      response.setErrorMsg(e.getMessage());
    }

    return response;
  }

  private Response<BillingForm> createRoomBilling(BillingForm form) {
    Response<BillingForm> resp = new Response<>();
    try {
      for (Transaction tx : form.getBills()) {
        Billing bill = new Billing();
        Date dueDate = DateUtil.addDays(DateUtil.getCurrentDate(), DateUtil.THIRTYDAYS);
        String billingNo = StringUtil.generateBillingNo(tx.getAptId(), tx.getRoomId());
        bill.setAmount(tx.getAmount());
        bill.setAptId(tx.getAptId());
        bill.setBillingNo(billingNo);
        bill.setDeposit(tx.getDeposit());
        bill.setOverdue(tx.getBalance());
        bill.setRenterId(tx.getRenterId());
        bill.setRoomId(tx.getRoomId());
        bill.setStatus(RentStatus.ACTIVE);
        bill.setTxDate(DateUtil.getCurrentDate());
        bill.setBillType(RentStatus.BILL_RENT);
        bill.setDueDate(dueDate);
        bill.setGenerationDate(DateUtil.getCurrentDate());

        // update the transaction data
        tx.setDueDate(dueDate);
        tx.setTxDate(DateUtil.getCurrentDate());
        tx.setUpdatedDate(DateUtil.getCurrentDate());
        tx.setPaymentStatus(RentStatus.ACTIVE);
        tx.setUserId(1);
        tx.setPaymentStatus(RentStatus.UNPAID);
        tx.setUpdtCnt(tx.getUpdtCnt() + 1);
        tx.setBillingNo(billingNo);

        billingDao.saveUpdate(bill);
        rentalDao.saveUpdate(tx);
      }

      rentalDao.commit();
      billingDao.commit();
      resp.setModel(form);
      resp.setResponseStatus(ResultStatus.RESULT_OK);
    } catch (Exception e) {
      logger.error("createNewBilling:" + e.getMessage(), e);
      resp.setErrorMsg(e.getLocalizedMessage());
      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
    }

    return resp;
  }

  @Override
  public Response<Transaction> createNewBilling(Renter renter) {
    Response<Transaction> response = new Response<Transaction>();
    // Billing bill = new Billing();
    try {
      // String billingNo = StringUtil.generateBillingNo(renter.getAptId(), renter.getRoomId());
      // bill.setAmount(0d);
      // bill.setAptId(renter.getAptId());
      // bill.setBillingNo(billingNo);
      // bill.setDeposit(0d);
      // bill.setOverdue(0d);
      // bill.setRenterId(renter.getId());
      // bill.setRoomId(renter.getRoomId());
      // bill.setStatus(RentStatus.ACTIVE);
      // bill.setTxDate(DateUtil.getCurrentDate());
      // bill.setBillType(RentStatus.BILL_RENT);
      Transaction tx = rentalDao.getTransactionByAptRoomRenter(renter.getAptId(),
          renter.getRoomId(), renter.getId());
      if (tx == null) {
        tx = new Transaction();
        tx.setAptId(renter.getAptId());
        tx.setRoomId(renter.getRoomId());
        tx.setRenterId(renter.getId());
        tx.setRoomId(renter.getRoomId());
        // tx.setUserId();
        tx.setDueDate(DateUtil.addDays(DateUtil.getCurrentDate(), DateUtil.THIRTYDAYS));
        tx.setStartDate(DateUtil.getCurrentDate());
        tx.setBalance(0d);
        tx.setDeposit(0d);
        tx.setTxType("cash");
        tx.setTxDate(DateUtil.getCurrentDate());
        tx.setPaymentStatus(RentStatus.ACTIVE);
        tx.setUserId(1);
        tx.setPaymentStatus(RentStatus.UNPAID);
        tx.setUpdtCnt(0);
      } else {
        tx.setDueDate(DateUtil.addDays(DateUtil.getCurrentDate(), DateUtil.THIRTYDAYS));
        tx.setStartDate(DateUtil.getCurrentDate());
        tx.setStatus(RentStatus.ACTIVE);
        tx.setBalance(0d);
        tx.setDeposit(0d);
        tx.setTxDate(DateUtil.getCurrentDate());
      }
      response.setResponseStatus(ResultStatus.RESULT_OK);
      // billingDao.saveUpdate(bill);
      rentalDao.saveUpdate(tx);
      rentalDao.commit();
      billingDao.commit();
    } catch (Exception e) {
      logger.error("createNewBilling:" + e.getMessage(), e);
      response.setErrorMsg(e.getMessage());
      response.setResponseStatus(ResultStatus.GENERAL_ERROR);
    }

    return response;
  }

  private PdfPTable createRentReceipt(Transaction tx) {
    PdfPTable table = new PdfPTable(2);
    // table.getDefaultCell().setBorder(Rectangle.BOX);

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

    // cell.addElement(new PdfPCell());;
    table.addCell(cell);
    table.getDefaultCell().setFixedHeight(30);
    table.addCell(
        PdfUtil.createCell("Ref #: " + tx.getBillingNo(), Element.ALIGN_LEFT, Rectangle.NO_BORDER));
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


    table.addCell(PdfUtil.createCell("Amount Due:", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell(String.valueOf(tx.getAmount()), Element.ALIGN_LEFT,
        Rectangle.NO_BORDER));

    table.addCell(PdfUtil.createCell("Over Due:", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell(String.valueOf(tx.getBalance()), Element.ALIGN_LEFT,
        Rectangle.NO_BORDER));
    Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    table.addCell(
        PdfUtil.createCell("Total:", Element.ALIGN_LEFT, Rectangle.BOX, 1, boldFont));
    table.addCell(PdfUtil.createCell(String.valueOf(tx.getAmountPayable()), Element.ALIGN_LEFT,
        Rectangle.BOX));
    PdfPCell spacer = new PdfPCell();
    spacer.setFixedHeight(30f);
    spacer.setColspan(2);
    spacer.setBorder(Rectangle.NO_BORDER);
    table.addCell(spacer);
    return table;
  }

}
