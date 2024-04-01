/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.presentacionbdavanzadas2;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.layout.Canvas;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
/**
 *
 * @author INEGI
 */
public class PageNumberEventHandler implements IEventHandler {
     protected PdfFont font;
    
    public PageNumberEventHandler(PdfFont font) {
        this.font = font;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        int pageNumber = pdfDoc.getPageNumber(page);
        Rectangle pageSize = page.getPageSize();
        Canvas canvas = new Canvas(page, pageSize);
        canvas.setFont(font).setFontSize(12);
        canvas.showTextAligned(String.valueOf(pageNumber),
                pageSize.getWidth() / 2, pageSize.getBottom() + 30,
                TextAlignment.CENTER);
    }
}
