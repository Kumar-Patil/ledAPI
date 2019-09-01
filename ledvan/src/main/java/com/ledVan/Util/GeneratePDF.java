package com.ledVan.Util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ledVan.model.Display;
import com.ledVan.model.LedDisplay;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 *
 * @author Santosh Patil
 */
public class GeneratePDF {

    public static String generatePDF(String pdfFilePath, String imagePath, List<LedDisplay> listLedDisplay, String date) {
        try {
            OutputStream file = new FileOutputStream(new File(pdfFilePath));
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            for (LedDisplay ledDisplay : listLedDisplay) {
                for (Display display : ledDisplay.getDisplay()) {
                    document.newPage();
                    //Create Paragraph
                    Paragraph paragraph = new Paragraph("Stage Light & Sound Status Report", new Font(Font.FontFamily.TIMES_ROMAN, 14,
                            Font.NORMAL));
                    paragraph.setAlignment(Element.ALIGN_CENTER);

                    document.add(paragraph);
                    Paragraph paragraph1 = new Paragraph(date, new Font(Font.FontFamily.TIMES_ROMAN, 14,
                            Font.NORMAL));
                    paragraph1.setAlignment(Element.ALIGN_CENTER);
                    paragraph1.add(new Paragraph(" "));
                    document.add(paragraph1);

                    //Create a table in PDF
                    PdfPTable pdfTable = new PdfPTable(4);
                    Font fontH1 = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL);
                    PdfPCell cell1 = new PdfPCell(new Phrase("District", fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase(ledDisplay.getDistrictName(), fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase("Area", fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase(ledDisplay.getDistrictAreaName(), fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    pdfTable.setHeaderRows(1);

                    cell1 = new PdfPCell(new Phrase("Vehicle No", fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase(ledDisplay.getVechicleNo(), fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase("Stay Area", fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase(ledDisplay.getVechicleStayArea(), fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase("Reporting Time", fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase(ledDisplay.getReportingTime(), fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase("Closing Time", fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase(ledDisplay.getClosingTime(), fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase("Closing Place", fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase(ledDisplay.getClosingPlace(), fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase("Distance", fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase(ledDisplay.getDistance(), fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase("Display Area", fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase(display.getDisplayArea(), fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase("Display Time", fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase(display.getDisplayTimingTo() + " To" + display.getDisplayTimingFrom(), fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase("Total View", fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase(display.getPeopleViewed(), fontH1));
                    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    pdfTable.addCell(cell1);
                    document.add(pdfTable);

                    Image image = Image.getInstance(imagePath + display.getDisplayPictureName());
                    image.scaleToFit(250, 250);
                    image.setAlignment(Image.ALIGN_CENTER);
                    document.add(image);
                }
            }
            document.close();
            file.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return pdfFilePath;
    }
}
