package com.sf.bcsp.base.export.excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class LoadExcel {
    public List<HSSFRow> loadRows() throws Exception{
        List<HSSFRow> rowList = new ArrayList<HSSFRow>();

        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("G:\\testtemp\\1.xls")));

        HSSFSheet sheet = null;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
            sheet = workbook.getSheetAt(i);
            for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {// getLastRowNum，获取最后一行的行标
                rowList.add(sheet.getRow(j));
            }
        }
        return rowList;
    }
}
