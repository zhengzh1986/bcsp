package com.sf.bcsp.base.export;

import com.sf.bcsp.base.export.template.ExcelTemplateCache;
import com.sf.bcsp.data.base.BcspDataReader;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 *  Excel 07 版本导出功能
 */
public class ExcelExport implements Export {

    private static final Logger logger = LoggerFactory.getLogger(ExcelExport.class);

    private int fileIndex = 0;
    private SXSSFWorkbook workBook;
    private SXSSFSheet sheettemp;
    private OutputStream os;
    private int currentRowNumber;
    private int MAX_COUNT_NUMBER = 30;


    @Override
    public void export(String fullName, String outFullName, Map<String, Object> baseParams, BcspDataReader bcspDataReader) {
        initExcel(outFullName);

        XSSFSheet sheet = ExcelTemplateCache.excelTemplateFactory(fullName).getXssfSheet();
        List<XSSFRow> rows = ExcelTemplateCache.excelTemplateFactory(fullName).getRowsList();
        Map<XSSFRow, List<CellRangeAddress>> initRangeMap = ExcelTemplateCache.excelTemplateFactory(fullName).getMap();
        Map<String, Object> valueMap = null;
        Map<String, Double> totalValueMap = new HashMap<>();

        for (int i = 0; i < rows.size(); i++) {
            SXSSFRow row = null;
            Optional<XSSFRow> currentRow = Optional.ofNullable(rows.get(i));

            String cellValue = currentRow.map(c -> c.getCell(0)).map(v -> v.getStringCellValue()).orElse("");

            if (cellValue.startsWith("{liststart::")) {
                String getListParams = cellValue.split("::")[1].split(":")[0];
                Map<String, Object> params = new HashMap<>();
                params.put("key999", getListParams.replace("}", ""));
                bcspDataReader.reparametrization(params);
                valueMap = bcspDataReader.nextReader();
                if (valueMap == null) {
                    i += 4;
                    continue;
                }
                // 清空数据
                row = PoiUtils.createNewRows(sheettemp, currentRowNumber++);
                PoiUtils.rowsListCellsCopy(workBook, rows.get(i), row, baseParams);
                PoiUtils.setMergedRegions(sheettemp, row, initRangeMap.get(rows.get(i)));
            } else if (cellValue.startsWith("{list::")) {
                totalValueMap = new HashMap<>();
                while (true) {
                    // 加个判断 如果行数 达到行数
                    if (currentRowNumber >= MAX_COUNT_NUMBER) {
                        closeCurrWorkBook();
                        currentRowNumber = 0;
                        fileIndex++;
                        initExcel(outFullName);
                    }
                    if (valueMap == null) {
                        valueMap = bcspDataReader.nextReader();
                    }
                    if (valueMap == null) {
                        break;
                    }
                    row = PoiUtils.createNewRows(sheettemp, currentRowNumber++);
                    PoiUtils.rowsCellsCopy(workBook, rows.get(i), row, valueMap, totalValueMap);
                    PoiUtils.setMergedRegions(sheettemp, row, initRangeMap.get(rows.get(i)));
                    valueMap = null;
                }
                baseParams.putAll(totalValueMap);
            } else if (cellValue.startsWith("{forlist::")) {
                // 判斷是否有值 listValues如沒值，則該行值為空
                int j = 0;
                HashMap<String, Object> tempMap = new HashMap<>();
                Set<Map.Entry<String, Object>> entrySets = baseParams.entrySet();
                for (Map.Entry<String, Object> entry : entrySets) {
                    if (entry.getKey().startsWith("name") && entry.getValue() != null) {
                        tempMap.put("showname" + j, baseParams.get("name" + j));
                        tempMap.put("showvalue" + j, baseParams.get("value" + j));
                        j++;
                    }
                }
                j = 0;
                Set<Map.Entry<String, Object>> entryNewSets = tempMap.entrySet();
                for (Map.Entry<String, Object> entry : entryNewSets) {
                    if (baseParams.get("name" + j) == null) {
                        break;
                    }
                    baseParams.put("showname", baseParams.get("name" + j));
                    baseParams.put("showvalue", baseParams.get("value" + j));
                    j++;
                    row = PoiUtils.createNewRows(sheettemp, currentRowNumber++);
                    PoiUtils.rowsListCellsCopy(workBook, rows.get(i), row, baseParams);
                    PoiUtils.setMergedRegions(sheettemp, row, initRangeMap.get(rows.get(i)));
                }

            } else {
                // 清空数据
                row = PoiUtils.createNewRows(sheettemp, currentRowNumber++);
                PoiUtils.rowsListCellsCopy(workBook, rows.get(i), row, baseParams);
                PoiUtils.setMergedRegions(sheettemp, row, initRangeMap.get(rows.get(i)));
            }
        }
        closeCurrWorkBook();
    }


    public String initExcel(String outputFileName) {
        XSSFWorkbook outputwork = null;
        try {
            String excelOutfileName = String.format(outputFileName, new Object[]{fileIndex});
            File f = new File(excelOutfileName);
            if (!f.exists()) {
                f.createNewFile();
            }
            os = new FileOutputStream(f);
            outputwork = new XSSFWorkbook(f);
        } catch (Exception e1) {
            logger.error("create Excel error:", e1);
        }
        workBook = new SXSSFWorkbook(outputwork);
        workBook.setCompressTempFiles(true);
        sheettemp = workBook.createSheet("月结对账单");
        return "";
    }

    public void closeCurrWorkBook(){
        try {
            workBook.write(os);
        } catch (IOException e) {
            logger.error("close outputStream error:",e);
        }
    }
}
