package com.shinn.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfUtil {
  
  public static final Font DEFAULT_FONT = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
  public static final Font DEFAULT_FONT_BOLD = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
  
  /**
   * create an open Document
   * @param fileLocation
   * @return
   * @throws FileNotFoundException
   * @throws DocumentException
   */
  public static Document createDocument(String fileLocation) throws FileNotFoundException, DocumentException {
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream(fileLocation));
    document.open();
    return document;
  }
  /**
   * 
   * @param text
   * @param alignment
   * @param border
   * @return
   */
  public static PdfPCell createCell(String text) {
    Phrase phrase = new Phrase(text);
    phrase.setFont(DEFAULT_FONT);
    PdfPCell cell = new PdfPCell(phrase);
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
  }
  /**
   * 
   * @param text
   * @param alignment
   * @param border
   * @return
   */
  public static PdfPCell createCell(String text, Integer alignment,Integer border) {
    Phrase phrase = new Phrase(text);
    phrase.setFont(DEFAULT_FONT);
    PdfPCell cell = new PdfPCell(phrase);
    cell.setHorizontalAlignment(alignment);
    cell.setBorder(border);
    return cell;
  }
  
  /**
   * 
   * @param text
   * @param alignment
   * @param border
   * @param colspan
   * @return
   */
  public static PdfPCell createCell(String text, Integer alignment,Integer border, Integer colspan) {
    Phrase phrase = new Phrase(text);
    phrase.setFont(DEFAULT_FONT);
    PdfPCell cell = new PdfPCell(phrase);
    cell.setHorizontalAlignment(alignment);
    cell.setBorder(border);
    cell.setColspan(colspan);
    return cell;
  }
  public static PdfPCell createCell(String text, Integer alignment,Integer border, Integer colspan, Font font) {
    Phrase phrase = new Phrase(text);
    phrase.setFont(font);
    PdfPCell cell = new PdfPCell(phrase);
    cell.setHorizontalAlignment(alignment);
    cell.setBorder(border);
    cell.setColspan(colspan);
    return cell;
  }

}
