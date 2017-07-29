package com.shinn.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.shinn.dao.factory.ResultStatus;
import com.shinn.dao.repos.ElectricBillDao;
import com.shinn.dao.repos.ElectricProviderDao;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.ElectricProvider;
import com.shinn.ui.model.BillingForm;
import com.shinn.ui.model.Response;
import com.shinn.util.PdfUtil;

@Service
public class BillingServiceImpl implements BillingService {
  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BillingServiceImpl.class);
  private static String FILE = "C:/Users/rbkshinn/FirstPdf.pdf";
  private static Integer NUMBER_OF_ROWS = 3;
  
  @Autowired
  ElectricBillDao electricBillDao;
  @Autowired
  ElectricProviderDao electricProviderDao;

  public void generateBilling(BillingForm billingForm) {
  
    try {
      Document document = PdfUtil.createDocument(FILE);
      for (ElectricBill electricBill : billingForm.getRooms()) {
        PdfPTable billTable = createElectricBill(electricBill, billingForm.getElectricProvider());
        document.add(billTable);
      }
      document.close();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }     
  }
  
  /**
   * format a bill to a pdf table
   * @param bill
   * @param provider
   * @return
   */
  public PdfPTable createElectricBill(ElectricBill bill, ElectricProvider provider) {
    PdfPTable table = new PdfPTable(NUMBER_OF_ROWS);

    String customerInfo = "Billing #:"+ bill.getBillingNo() + "Room #: " +bill.getRoomId() + "\t" + "Meter #: " + bill.getMeterNo();
    table.addCell(PdfUtil.createCell(customerInfo, Element.ALIGN_CENTER, Rectangle.NO_BORDER, NUMBER_OF_ROWS));
    //Rate
    table.addCell(PdfUtil.createCell("RATE",Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS));
    
    table.addCell(PdfUtil.createCell("Generation Charge"));
    table.addCell(PdfUtil.createCell(provider.getGenerationCharge() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getGenerationCharge())));
    
    table.addCell(PdfUtil.createCell("Transmission Charge"));
    table.addCell(PdfUtil.createCell(provider.getTransmissionCharge() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getTransmissionCharge())));

    table.addCell(PdfUtil.createCell("System Loss Charge"));
    table.addCell(PdfUtil.createCell(provider.getSystemLoss() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getSystemLossCharge())));
    
    //subtotal here
    table.addCell(PdfUtil.createCell("Subtotal 1",Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getSubTotal1()),Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    
   //distriution revenues  
    table.addCell(PdfUtil.createCell("Distribution Revenues",Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS, PdfUtil.DEFAULT_FONT_BOLD));

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
    
    //subtotal 2 here
    table.addCell(PdfUtil.createCell("Subtotal 2",Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getSubTotal2()),Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    
     //for MECO need to confirm othes bill since its negative.
    //for VECO multiply
    //other, life line discount
    table.addCell(PdfUtil.createCell("Others",Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS, PdfUtil.DEFAULT_FONT_BOLD));
    
    table.addCell(PdfUtil.createCell("Lifeline Discount"));
    table.addCell(PdfUtil.createCell(provider.getTransmissionCharge() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getTransmissionCharge())));
    
    if (bill.getSurcharge() != null) {
      table.addCell(PdfUtil.createCell("Surcharge"));
      table.addCell(PdfUtil.createCell(provider.getSurcharge() + "/kwh"));
      table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getSurcharge())));
    }
    
    //subtotal 3
    table.addCell(PdfUtil.createCell("Subtotal 3",Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getSubTotal3()),Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    
    //government revenues
    table.addCell(PdfUtil.createCell("Government Revenues",Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS));
    
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
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getTax().getMissionaryElectrification())));
    
    table.addCell(PdfUtil.createCell("Environmental Charge"));
    table.addCell(PdfUtil.createCell(provider.getEnvironmentalCharge() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getTax().getEnvironmental())));
    
    table.addCell(PdfUtil.createCell("Fit All Renewable"));
    table.addCell(PdfUtil.createCell(provider.getFitAllRenewable() + "/kwh"));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getTax().getFitAllRenewable())));
    
    //subtotal 4
    table.addCell(PdfUtil.createCell("Subtotal 4",Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getSubTotal3()),Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    
    //gross amount
    table.addCell(PdfUtil.createCell("Gross Amount",Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getGrossAmount())));
    
    //current amount
    table.addCell(PdfUtil.createCell("Current Amount",Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getCurrentAmount())));
    
    table.addCell(PdfUtil.createCell("Overdue",Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getOverdue())));
    
    //total amount
    table.addCell(PdfUtil.createCell("Total Amount Due",Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS - 1, PdfUtil.DEFAULT_FONT_BOLD));
    table.addCell(PdfUtil.createCell(String.valueOf(bill.getBill().getGrossAmount())));
   
    //disconnection Notice
    table.addCell(PdfUtil.createCell("Disconnection Due Date: 48 hours from receipt hereof",Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS, PdfUtil.DEFAULT_FONT_BOLD));
   
    //last payment
    //query from collections //TODO
    table.addCell(PdfUtil.createCell("Last Payment",Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS, PdfUtil.DEFAULT_FONT));
    
    //spacing for the nex bill
    for (int i = 0; i < NUMBER_OF_ROWS; i++) {
      table.addCell(PdfUtil.createCell("",Element.ALIGN_LEFT, Rectangle.NO_BORDER, NUMBER_OF_ROWS, PdfUtil.DEFAULT_FONT));
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
      java.util.List<ElectricBill> electricBills =  electricBillDao.getElectricBillByApt(aptId);
      Iterator<ElectricBill> itr = electricBills.iterator();
      while (itr.hasNext()) {
        ElectricBill electricBill = itr.next();
        electricBill.setElectricProvider(electricProviderDao.getProvider(electricBill.getProvider()));
      }
      resp.setResult(electricBills);
      resp.setResponseStatus(ResultStatus.RESULT_OK);
    } catch (Exception e) {
      resp.setErrorMsg(e.getMessage());
      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
    }
    return resp;
   
  }


}
