package com.sf.bcsp.base.export;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import com.sf.bcsp.base.utils.BeetlFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PdfExport {

    private PdfDocument pdfMainDocument;

    private Document pdfDocument;

    private PdfWriter pdfWriter;

    private Rectangle pdfRectangle = new Rectangle(653.84f, 877.03f);

    private PageSize pdfPageSize = new PageSize(pdfRectangle);

    private PdfFont titleFont;

    private PdfPage currentPage;

    private int rowIndex = 0;

    public void initPdfDocument() {

        String fileName = String.format("itext-pdf-%s.pdf", "20180404");

        String newPDFPath = "G:\\testtemp\\pdf\\" + fileName;
        try {
            titleFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);

            pdfWriter = new PdfWriter(new FileOutputStream(newPDFPath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        pdfWriter.setCompressionLevel(9);

        pdfMainDocument = new PdfDocument(pdfWriter);

        pdfMainDocument.setDefaultPageSize(pdfPageSize);

//        pdfMainDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new PdfEndPageEvent());

        pdfDocument = new Document(pdfMainDocument);


    }

    public void writerPdf(List<HSSFRow> tempRows, Map<String, Object> dataMap) {

        for (int i=0;i<tempRows.size();i++) {
            HSSFRow row = tempRows.get(i);
            if(row==null)
            {
                pdfDocument.add(new Paragraph("\r\n\r\n"));
                continue;
            }
            HSSFCell cell = row.getCell(0);
            String cellValue = cell.getStringCellValue();
            if (StringUtils.startsWith(cellValue, "{list:")) {
                appendByRow(row, dataMap);
            } else if (StringUtils.startsWith(cellValue, "{listloop:")) {
                dataMap.put("_KEY999_","");
                while(true)
                {
                    if(!appendByRow(row,dataMap))
                    {
                        dataMap.remove("_KEY999_");
                        break;
                    }
                }
            }else
            {
                appendByRow(row, dataMap);
            }

        }
    }

    private boolean appendByRow(HSSFRow row, Map<String, Object> dataMap) {
        float[] columnsWidth = new float[row.getLastCellNum()];
        Table table = new Table(columnsWidth);
        if (dataMap.containsKey("_KEY999_"))
        {
            while(true)
            {
                Map<String,Object> dbMap = new HashMap<String, Object>();
                if(dbMap==null)
                {
                    dataMap.remove("_KEY999_");
                    return  false;
                }
                //创建
                createRowCellLoop(row,dataMap);
            }
        }
        createRowCellLoop(row,dataMap);
        return  false;
    }

    private void createRowCellLoop(HSSFRow row, Map<String, Object> dataMap){
        rowIndex++;
        for (int i = 0; i < row.getLastCellNum(); i++) {
            pdfDocument.add(renderPdfCell(row.getCell(i),dataMap));
        }
    }

    private Cell renderPdfCell(HSSFCell hssfCell, Map<String, Object> dataMap) {
        Cell pdfCell = new Cell(rowIndex, hssfCell.getColumnIndex());
        HSSFCellStyle xlsxStyle = hssfCell.getCellStyle();
        pdfCell.add(new Paragraph(hssfCell.getStringCellValue()));
        pdfCell.setFont(titleFont);
        pdfCell.setBorderBottom(xlsxStyle.getBorderBottomEnum().getCode() == (short) 1 ? new DashedBorder(SolidBorder.DASHED) : SolidBorder.NO_BORDER);
        pdfCell.setBorderLeft(xlsxStyle.getBorderLeftEnum().getCode() == (short) 1 ? new DashedBorder(SolidBorder.DASHED) : SolidBorder.NO_BORDER);
        pdfCell.setBorderRight(xlsxStyle.getBorderRightEnum().getCode() == (short) 1 ? new DashedBorder(SolidBorder.DASHED) : SolidBorder.NO_BORDER);
        pdfCell.setBorderTop(xlsxStyle.getBorderTopEnum().getCode() == (short) 1 ? new DashedBorder(SolidBorder.DASHED) : SolidBorder.NO_BORDER);
        pdfCell.setHorizontalAlignment(HorizontalAlignment.CENTER);
        pdfCell.setVerticalAlignment(VerticalAlignment.MIDDLE);
        pdfCell.add(BeetlFactory.beetlFactory().render(hssfCell.getStringCellValue(), dataMap));
        return pdfCell;
    }


    public void close() {
        pdfDocument.close();
        try {
            pdfWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfMainDocument.close();
    }
}
