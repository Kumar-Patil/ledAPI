/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledVan;

/**
 *
 * @author santopat
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Date;

public class PDFConversionDemo {

    public static void main(String[] args) {
        System.out.println("start Date" + new Date());
        try {
            OutputStream file = new FileOutputStream(new File("C:\\ledVan\\ledvan\\SamplePDF.pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            for(int i=0; i<200 ; i++)
            {
            document.newPage();
            //Create Paragraph
            Paragraph paragraph = new Paragraph("Stage Light & Sound Status Report", new Font(Font.FontFamily.TIMES_ROMAN, 14,
                    Font.NORMAL));
            paragraph.setAlignment(Element.ALIGN_CENTER);
            
            document.add(paragraph);
            Paragraph paragraph1 = new Paragraph("01 Aug 2019", new Font(Font.FontFamily.TIMES_ROMAN, 14,
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

            cell1 = new PdfPCell(new Phrase("Bokaro", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Area", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Bokaro 1", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);

            pdfTable.setHeaderRows(1);

            cell1 = new PdfPCell(new Phrase("Vehicle No", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("HR67B0858", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("Stay Area", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("POLICE STATION", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("Reporting Time", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("9:00 AM", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("Closing Time", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("9:00 PM", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
                        
            cell1 = new PdfPCell(new Phrase("Closing Place", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("POLICE STATION", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("Distance", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("70 Km", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
          
            cell1 = new PdfPCell(new Phrase("Display Area", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("HATKATHI PANCHAYAT", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("Display Time", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("4:45 PM To 8:00 PM - 3 Hrs 15 Min", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
           
            cell1 = new PdfPCell(new Phrase("Total View", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("150-200", fontH1));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfTable.addCell(cell1);
            document.add(pdfTable);

            Image image = Image.getInstance("C:\\ledVan\\ledvan\\led.png");
            image.scaleToFit(250,250);
            image.setAlignment(Image.ALIGN_CENTER);
            document.add(image);
            }
            document.close();
            file.close();
            
        } catch (Exception e) {

            e.printStackTrace();
        }
        System.out.println("End Date" + new Date());
       
    }

}
