import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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
    document.add(createFirstTable());
    // step 5
    document.close();
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
