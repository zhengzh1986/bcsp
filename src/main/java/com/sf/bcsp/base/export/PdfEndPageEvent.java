package com.sf.bcsp.base.export;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import java.io.IOException;

public class PdfEndPageEvent implements IEventHandler {
    PdfFont font;
    public void handleEvent(Event event) {
        try {
            PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        int pageNumber = pdfDoc.getPageNumber(page);
        Rectangle pageSize = page.getPageSize();
        PdfCanvas pdfCanvas = new PdfCanvas(
                page.newContentStreamBefore(), page.getResources(), pdfDoc);

        //Add header and footer
        pdfCanvas.beginText()
                .setFontAndSize(font, 9)
                .moveText(pageSize.getWidth() / 2 - 60, pageSize.getTop() - 20)
                .showText("THE TRUTH IS OUT THERE")
                .moveText(60, -pageSize.getTop() + 30)
                .showText(String.valueOf(pageNumber))
                .endText();
        pdfCanvas.release();
    }
}
