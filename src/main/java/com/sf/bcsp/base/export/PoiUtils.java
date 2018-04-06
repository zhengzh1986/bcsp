package com.sf.bcsp.base.export;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiUtils {



	public static Map<String, Object> rowsCellsCopy(SXSSFWorkbook wb, XSSFRow srow, SXSSFRow destRow,
			Map<String, Object> params, Map<String, Double> totalValueMap) {
		// Map<String,Object> resultMap = new HashMap<>();

		for (Iterator cellIt = srow.cellIterator(); cellIt.hasNext();) {
			XSSFCell tmpCell = (XSSFCell) cellIt.next();

			SXSSFCell newCell = createNewCell(wb, destRow, tmpCell.getColumnIndex(), tmpCell.getCellStyle());

			copyCell(tmpCell, newCell, params, totalValueMap);

			// resultMap.put(tmpCell.getStringCellValue(), val);
		}
		return null;
	}

	public static void rowsListCellsCopy(SXSSFWorkbook wb, XSSFRow srow, SXSSFRow destRow,
			Map<String, Object> valueMap) {
		Map<String, Double> totalMap = new HashMap<String, Double>();
		for (Iterator cellIt = srow.cellIterator(); cellIt.hasNext();) {
			XSSFCell tmpCell = (XSSFCell) cellIt.next();

			SXSSFCell newCell = createNewCell(wb, destRow, tmpCell.getColumnIndex(), tmpCell.getCellStyle());

			copyCell(tmpCell, newCell, valueMap, totalMap);
		}
	}

	public static void setMergedRegions(SXSSFSheet sheettemp, XSSFSheet currentSheet) {
		CellRangeAddress region = null;
		for (int i = 0; i < currentSheet.getNumMergedRegions(); i++) {
			region = currentSheet.getMergedRegion(i);
			CellRangeAddress newCellRangeAddress = region.copy();
			sheettemp.addMergedRegionUnsafe(newCellRangeAddress);
		}
	}



	/**
	 * 设置 合并单元格
	 * 
	 * @param sheettemp
	 * @param currentRow
	 * @param cellRangeAddressList
	 */
	public static void setMergedRegions(SXSSFSheet sheettemp, SXSSFRow currentRow,
			List<CellRangeAddress> cellRangeAddressList) {
		if (cellRangeAddressList != null) {
			cellRangeAddressList.forEach(cc -> {
				CellRangeAddress newCellRangeAddress = new CellRangeAddress(currentRow.getRowNum(),
						(currentRow.getRowNum() + (cc.getLastRow() - cc.getFirstRow())), cc.getFirstColumn(),
						cc.getLastColumn());
				sheettemp.addMergedRegionUnsafe(newCellRangeAddress);
			});
		}

	}

	/**
	 * 复制单元格
	 * 
	 * @param tmpCell
	 * @param newCell
	 * @param params
	 *            {liststart::1:} {listhead::1:序号} {list::1:{id}} {listend::1:小
	 *            计}
	 */
	public static double copyCell(XSSFCell tmpCell, SXSSFCell newCell, Map<String, Object> params,
			Map<String, Double> totalValueMap) {
		String value = tmpCell.getStringCellValue();
		Object objValue = value;

		if (value.startsWith("{")) {
			String excelKey = value.substring(1, value.length() - 1);
			String[] keys = excelKey.split(":");
			String lastKey = keys[1].replace("{", "").replace("}", "");
			if (keys.length == 2 && params.containsKey(lastKey)) {
				objValue = params.get(lastKey);
				if ("s".equals(keys[0])) {
					Double val = Double.valueOf(String.valueOf(
							totalValueMap.get("sum" + keys[1]) == null ? "0" : totalValueMap.get("sum" + keys[1])))
							+ Double.valueOf(String.valueOf(objValue));
					totalValueMap.put("sum" + keys[1], val);
					newCell.setCellValue(String.valueOf(objValue));
					return 0;
				}
				if (value.contains("{") && !value.startsWith("{")) {
					objValue = value.substring(0, value.indexOf("{")) + String.valueOf(objValue)
							+ value.substring(value.indexOf("}") + 1, value.length());
				}
			} else {
				objValue = lastKey;
			}
		}
		newCell.setCellValue(String.valueOf(objValue));
		return 0;
	}

	/**
	 * 创建合并单元格
	 * 
	 * @param sheet
	 * @param cRowNumber
	 * @param cellRangeAddress
	 * @return int firstRow, int lastRow, int firstCol, int lastCol
	 */
	public static SXSSFRow addMergedRegion(SXSSFSheet sheet, int cRowNumber, CellRangeAddress cellRangeAddress) {
		sheet.addMergedRegion(cellRangeAddress);
		return createNewRows(sheet, cRowNumber);
	}

	/**
	 * 创建行
	 * 
	 * @param sheet
	 * @param cRowNumber
	 * @return
	 */
	public static SXSSFRow createNewRows(SXSSFSheet sheet, int cRowNumber) {
		return sheet.createRow(cRowNumber);
	}

	/**
	 * 创建SXSSFCell格子
	 * 
	 * @param row
	 * @param columnNumber
	 * @param cellType
	 * @return
	 */
	public static SXSSFCell createNewCell(SXSSFWorkbook wb, SXSSFRow row, int columnNumber, XSSFCellStyle cellType) {
		CellStyle newStyle = wb.createCellStyle();
		SXSSFCell cell = row.createCell(columnNumber);
		newStyle.cloneStyleFrom(cellType);
		cell.setCellStyle(newStyle);
		return cell;
	}

	/**
	 * 
	 * @param cell
	 *            获取的单元格
	 * @return 返回单元格中的值
	 */
	public static Object getCellValue(Cell cell) {
		// 定义返回值
		Object objResult = null;

		// 判断单元格中的值
		if (cell != null) {
			// 匹配格式类型
			switch (cell.getCellType()) {

			// 字符串类型
			case Cell.CELL_TYPE_STRING:

				objResult = cell.getRichStringCellValue().getString();

				break;
			// 货币类型
			case Cell.CELL_TYPE_NUMERIC:

				if (DateUtil.isCellDateFormatted(cell)) {
					objResult = cell.getDateCellValue();
				} else {
					objResult = cell.getNumericCellValue();
				}

				break;
			// 布尔类型
			case Cell.CELL_TYPE_BOOLEAN:

				objResult = cell.getBooleanCellValue();

				break;
			// 公式
			case Cell.CELL_TYPE_FORMULA:
				try {
					objResult = cell.getNumericCellValue();
				} catch (IllegalStateException e) {
					objResult = String.valueOf(cell.getRichStringCellValue());
				}
			default:
			}
		}

		// 返回取到的单元格值
		return objResult;
	}

	/**
	 * 复制单元格
	 * 
	 * @param currentSheet
	 *            sheet页
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @param pPosition
	 *            目标位置
	 */
	public static void copyRows(Sheet currentSheet, int startRow, int endRow, int pPosition) {

		int pStartRow = startRow - 1;
		int pEndRow = endRow - 1;
		int targetRowFrom;
		int targetRowTo;
		int columnCount;
		CellRangeAddress region = null;
		int i;
		int j;

		if (pStartRow == -1 || pEndRow == -1) {
			return;
		}

		for (i = 0; i < currentSheet.getNumMergedRegions(); i++) {
			region = currentSheet.getMergedRegion(i);
			if ((region.getFirstRow() >= pStartRow) && (region.getLastRow() <= pEndRow)) {
				targetRowFrom = region.getFirstRow() - pStartRow + pPosition;
				targetRowTo = region.getLastRow() - pStartRow + pPosition;
				CellRangeAddress newRegion = region.copy();
				newRegion.setFirstRow(targetRowFrom);
				newRegion.setFirstColumn(region.getFirstColumn());
				newRegion.setLastRow(targetRowTo);
				newRegion.setLastColumn(region.getLastColumn());
				currentSheet.addMergedRegion(newRegion);
			}
		}

		for (i = pStartRow; i <= pEndRow; i++) {
			XSSFRow sourceRow = (XSSFRow) currentSheet.getRow(i);
			columnCount = sourceRow.getLastCellNum();
			if (sourceRow != null) {
				XSSFRow newRow = (XSSFRow) currentSheet.createRow(pPosition - pStartRow + i);
				newRow.setHeight(sourceRow.getHeight());
				for (j = 0; j < columnCount; j++) {
					XSSFCell templateCell = sourceRow.getCell(j);
					if (templateCell != null) {
						XSSFCell newCell = newRow.createCell(j);
						copyCell(templateCell, newCell);
					}
				}
			}
		}
	}

	public static void copyCell(XSSFCell srcCell, XSSFCell distCell) {
		distCell.setCellStyle(srcCell.getCellStyle());
		if (srcCell.getCellComment() != null) {
			distCell.setCellComment(srcCell.getCellComment());
		}
		int srcCellType = srcCell.getCellType();
		distCell.setCellType(srcCellType);
		if (srcCellType == XSSFCell.CELL_TYPE_NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(srcCell)) {
				distCell.setCellValue(srcCell.getDateCellValue());
			} else {
				distCell.setCellValue(srcCell.getNumericCellValue());
			}
		} else if (srcCellType == XSSFCell.CELL_TYPE_STRING) {
			distCell.setCellValue(srcCell.getRichStringCellValue());
		} else if (srcCellType == XSSFCell.CELL_TYPE_BLANK) {
			// nothing21
		} else if (srcCellType == XSSFCell.CELL_TYPE_BOOLEAN) {
			distCell.setCellValue(srcCell.getBooleanCellValue());
		} else if (srcCellType == XSSFCell.CELL_TYPE_ERROR) {
			distCell.setCellErrorValue(srcCell.getErrorCellValue());
		} else if (srcCellType == XSSFCell.CELL_TYPE_FORMULA) {
			distCell.setCellFormula(srcCell.getCellFormula());
		} else { // nothing29

		}
	}

}
