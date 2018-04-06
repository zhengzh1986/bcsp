package com.sf.bcsp.base.export.template;

import com.sf.bcsp.base.entity.EmptoyObject;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelTemplateCache {


    private static HashMap<String,ExcelTemplateCache> cacheManager = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(ExcelTemplateCache.class);

    private String fileFullName;

    private List<XSSFRow> rowsList = new ArrayList<>();

    private XSSFSheet xssfSheet;

    public List<XSSFRow> getRowsList() {
        return rowsList;
    }

    public void setRowsList(List<XSSFRow> rowsList) {
        this.rowsList = rowsList;
    }

    public XSSFSheet getXssfSheet() {
        return xssfSheet;
    }

    public void setXssfSheet(XSSFSheet xssfSheet) {
        this.xssfSheet = xssfSheet;
    }

    public XSSFWorkbook getXssfWorkbook() {
        return xssfWorkbook;
    }

    public void setXssfWorkbook(XSSFWorkbook xssfWorkbook) {
        this.xssfWorkbook = xssfWorkbook;
    }

    public Map<XSSFRow, List<CellRangeAddress>> getMap() {
        return map;
    }

    public void setMap(Map<XSSFRow, List<CellRangeAddress>> map) {
        this.map = map;
    }

    private XSSFWorkbook xssfWorkbook;

    Map<XSSFRow, List<CellRangeAddress>> map = new HashMap<>();

    public ExcelTemplateCache(String fileFullName) {
        this.fileFullName = fileFullName;
    }

    public static ExcelTemplateCache excelTemplateFactory(String fileFullName){
        ExcelTemplateCache cacheExcel =  cacheManager.get(fileFullName);
        if (cacheExcel==null)
        {
            cacheExcel = new ExcelTemplateCache(fileFullName);
            cacheManager.put(fileFullName,cacheExcel);
            return cacheExcel;
        }
        return null;
    }

    public void initSheet() {
        xssfWorkbook = new XSSFWorkbook();
        xssfSheet = xssfWorkbook.getSheetAt(0);
        try {
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) {
                    continue;
                }
                rowsList.add(xssfRow);
            }
        } catch (Exception ex) {
            logger.error("initRows", ex);
        }
        rowsList.forEach(cc -> {
            List<CellRangeAddress> tmpList = new ArrayList<>();
            for (int i = 0; i < xssfSheet.getNumMergedRegions(); i++) {
                CellRangeAddress cellRangeAddress = xssfSheet.getMergedRegion(i);
                if (cellRangeAddress.getFirstRow() == cc.getRowNum()) {
                    tmpList.add(cellRangeAddress);
                }
            }
            map.put(cc, tmpList);
        });
    }
}
