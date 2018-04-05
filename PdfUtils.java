package com.sf.bcsp.base.export;

import com.sf.bcsp.base.export.excel.LoadExcel;
import org.apache.poi.hssf.usermodel.HSSFRow;

import java.util.HashMap;
import java.util.List;

public class PdfUtils {
    public static void main(String[] args)
    {
        LoadExcel loadExcel = new LoadExcel();
        try {
            List<HSSFRow> rowList =  loadExcel.loadRows();
            PdfExport pdfExport = new PdfExport();
            pdfExport.initPdfDocument();
            pdfExport.writerPdf(rowList,new HashMap<String, Object>());
            pdfExport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
