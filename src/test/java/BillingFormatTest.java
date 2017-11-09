import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Font.FontStyle;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.shinn.util.DateUtil;
import com.shinn.util.PdfUtil;

public class BillingFormatTest {


  /** The resulting PDF file. */
  public static final String RESULT = "C:/Users/rbkshinn/FirstPdf.pdf";

  /**
   * Main method.
   * 
   * @param args no arguments needed
   * @throws DocumentException
   * @throws IOException
   */
  public static void main(String[] args) throws IOException, DocumentException {
    new BillingFormatTest().createPdf(RESULT);
  }

  /**
   * Creates a PDF with information about the movies
   * 
   * @param filename the name of the PDF file that will be created.
   * @throws DocumentException
   * @throws IOException
   */
  public void createPdf(String filename) throws IOException, DocumentException {
    // step 1
    Document document = new Document();
    // step 2
    PdfWriter.getInstance(document, new FileOutputStream(filename));
    // step 3
    document.open();
    // step 4
//    document.add(createFirstTable());r
    document.add(createRoomReceipt());
    // step 5
    document.close();
  }
  
  public static PdfPTable createRoomReceipt() {
    PdfPTable table = new PdfPTable(2);
//    table.getDefaultCell().setBorder(Rectangle.BOX);
 
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
    
    
    
//    cell.addElement(new PdfPCell());;
    table.addCell(cell);
    table.getDefaultCell().setFixedHeight(30);
    table.addCell(PdfUtil.createCell("Ref #:" + "12312323213123", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell("Date :" + DateUtil.getCurrentDate(), Element.ALIGN_LEFT, Rectangle.NO_BORDER));

//    table.getDefaultCell().setFixedHeight(20);
    table.addCell(PdfUtil.createCell("Customer Name:", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell("name of customer", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell("Apartment Name:", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell("apartment name", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    
    table.addCell(PdfUtil.createCell("Room # :", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell("room no.", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    
    
    table.addCell(PdfUtil.createCell("Amount Due:", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell("0180123821", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    
    table.addCell(PdfUtil.createCell("Over Due:", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell("0180123821", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    
    table.addCell(PdfUtil.createCell("Total:", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    table.addCell(PdfUtil.createCell("0180123821", Element.ALIGN_LEFT, Rectangle.NO_BORDER));
    
    
//    cell.addElement(PdfUtil.createCell("", Element.ALIGN_CENTER, Rectangle.BOX,
//        3));
//    
//    cell.addElement(PdfUtil.createCell("Apartment Name",Element.ALIGN_LEFT, Rectangle.BOX));
//    cell.addElement(PdfUtil.createCell("Room Name",Element.ALIGN_LEFT, Rectangle.BOX));
//    cell.addElement(PdfUtil.createCell("renter name",Element.ALIGN_LEFT, Rectangle.BOX));
//    
//    
//    cell.addElement(
//        PdfUtil.createCell("Amount payable for the month of September:", Element.ALIGN_LEFT, Rectangle.NO_BORDER, 3));
//    cell.addElement(
//        PdfUtil.createCell("10000", Element.ALIGN_LEFT, Rectangle.NO_BORDER, 3));
//
//    cell.addElement(
//        PdfUtil.createCell("", Element.ALIGN_LEFT, Rectangle.NO_BORDER, 3));


        return table;
  }

  /**
   * Creates our first table
   * 
   * @return our first table
   */
  public static PdfPTable createFirstTable() {
    PdfPTable table = new PdfPTable(3);

    PdfPCell customerInfo = new PdfPCell(new Phrase("Table Header 1"));
    customerInfo.setHorizontalAlignment(Element.ALIGN_CENTER);
//    customerInfo.addElement(new Phrase());
    customerInfo.addElement(new Phrase("Customer name 1\t" + "Room #: 5" +"\t" + "Meter #: 5"));
    customerInfo.setColspan(3);
    customerInfo.setBorder(0);
    
    table.addCell(customerInfo);
    PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
    c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    table.addCell(c1);

    c1 = new PdfPCell(new Phrase("Table Header 2"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);

    c1 = new PdfPCell(new Phrase("Table Header 3"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);
//    table.addCell("Room Number :");
//    table.addCell("Meter #: ");
//    table.addCell("Name: ");
    
    table.addCell("Generation Charge");
    table.addCell("0.0042/kwh");
    table.addCell("50923.00");
    
    table.addCell("Transmission Charge");
    table.addCell("0.0042/kwh");
    table.addCell("50923.00");
    
    table.addCell("System Loss Charge");
    table.addCell("0.0042/kwh");
    table.addCell("50923.00");
    
    table.addCell("Distribution Revenues");
    table.addCell("0.0042/kwh");
    table.addCell("50923.00");
    
    table.addCell("Supply Charge");
    table.addCell("0.0042/kwh");
    table.addCell("50923.00");
    
    table.addCell("Metering Charge");
    table.addCell("0.0042/kwh");
    table.addCell("50923.00");
    
    table.addCell("Retail Customer Charge");
    table.addCell("0.0042/kwh");
    table.addCell("50923.00");
    
    table.addCell("Metering System Charge");
    table.addCell("0.0042/kwh");
    table.addCell("50923.00");
    
    table.addCell("Metering System Charge");
    table.addCell("0.0042/kwh");
    table.addCell("50923.00");
    
    table.addCell("Others");
    table.addCell("0.0042/kwh");
    table.addCell("50923.00");
    
    table.addCell("Lifeline Discount");
    table.addCell("0.0042/kwh");
    table.addCell("50923.00");
    
    table.addCell("Metering System Charge");
    table.addCell("0.0042/kwh");
    table.addCell("50923.00");
    
    table.addCell("Government Revenues");
    table.addCell("0.0042/kwh");
    table.addCell("50923.00");
    
    table.addCell("Local Franchise Tax");
    table.addCell("0.0042/kwh");
    table.addCell("50923.00");
    
    table.addCell("Value Added Tax");
    table.addCell("0.0042/kwh");
    table.addCell("50923.00");
    
    table.addCell("Generation/Transmission");
    table.addCell("0.0042/kwh");
    table.addCell("50923.00");
    
    table.addCell("Distribution");
    table.addCell("Others");
    table.addCell("Universal Charge");
    table.addCell("NPC Standard Cost");
    table.addCell("Missionary Electrification");
    table.addCell("Environmental Charge");
    table.addCell("Fit All Renewable");
    table.addCell("Sub total 4");
    table.addCell("Gross Amount");
    table.addCell("Current Amount");
    table.addCell("Advance Payment");
    table.addCell("TOTAL AMOUNT DUE");
    
   
    return table;
  }
  
  public static PdfPCell createCell(String text, Integer alignment,Integer border) {
    PdfPCell cell = new PdfPCell(new Phrase(text));
    cell.setHorizontalAlignment(alignment);
    cell.setBorder(border);
    return cell;
  }

}
