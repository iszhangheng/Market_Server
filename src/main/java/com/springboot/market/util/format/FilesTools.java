package com.springboot.market.util.format;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.springboot.market.util.format.DateTools;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FilesTools {

    private static final String EXCEL_XLS = ".xls";
    private static final String EXCEL_XLSX = ".xlsx";

    /**
     * 读取excel数据
     *
     * @throws Exception
     */
    public static List<List<String>> readExcelInfo(String url) throws Exception {
        // workbook:工作簿,就是整个Excel文档
        // sheet:工作表
        // row:行
        // cell:单元格
        File excelFile = new File(url);// 创建excel文件对象
        InputStream is = new FileInputStream(excelFile);// 创建输入流对象
        checkExcelVaild(excelFile);// 判断是否是EXCEL表格
        Workbook workbook = getWorkBook(is, excelFile); // 通过文件名将文件从流中取出
        // Workbook workbook = WorkbookFactory.create(is);//同时支持2003、2007、2010
        // 获取Sheet数量
        int sheetNum = workbook.getNumberOfSheets();
        // 创建二维数组保存所有读取到的行列数据，外层存行数据，内层存单元格数据
        List<List<String>> dataList = new ArrayList<List<String>>();
        // FormulaEvaluator formulaEvaluator = null;
        // 遍历工作簿中的sheet,第一层循环所有sheet表
        for (int index = 0; index < sheetNum; index++) {
            Sheet sheet = workbook.getSheetAt(index);
            if (sheet == null) {
                continue;
            }
            System.out.println("工作表行数:" + sheet.getLastRowNum());
            // 如果当前行没有数据跳出循环，第二层循环单sheet表中所有行
            for (int rowIndex = 0, rowNum = sheet.getLastRowNum(); rowIndex <= rowNum; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                // 根据文件头可以控制从哪一行读取，在下面if中进行控制
                if (row == null) {
                    continue;
                }
                // 遍历每一行的每一列，第三层循环行中所有单元格
                List<String> cellList = new ArrayList<String>();
                for (int cellIndex = 0, cellNum = row.getLastCellNum(); cellIndex < cellNum; cellIndex++) {
                    Cell cell = row.getCell(cellIndex); // 获取指定索引的单元格
                    cellList.add(getCellValue(cell));// 获取单元格内容
                }
                if (checkRowNull(cellList)) {
                    dataList.add(cellList);
                }
            }
        }
        is.close();
        return dataList;
    }

    /**
     * 获取单元格的数据
     *
     * @param cell 单元格
     * @return
     */
    public static String getCellValue(Cell cell) {
        if (cell == null || cell.toString().trim().equals("")) {
            return null;
        }
        CellType cellType = cell.getCellTypeEnum();
        String cellValue = "";
        if (cellType == CellType.STRING) {
            cellValue = cell.getStringCellValue().trim();
            return cellValue = cellValue == null ? "" : cellValue;
        }
        if (cellType == CellType.NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) { // 判断日期类型
                cellValue = DateTools.transformTimestampToDate(cell.getDateCellValue().getTime(), "yyyyMMdd");
            } else { // 否
                cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
            }
            return cellValue;
        }
        if (cellType == CellType.BOOLEAN) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
            return cellValue;
        }
        return null;

    }

    /**
     * 判断excel的版本，并根据文件流数据获取workbook
     */
    public static Workbook getWorkBook(InputStream is, File file) throws Exception {

        Workbook workbook = null;
        if (file.getName().endsWith(EXCEL_XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {
            workbook = new XSSFWorkbook(is);
        }

        return workbook;
    }

    /**
     * 判断传入的问价您是否是EXCEL
     *
     * @param file
     * @throws Exception
     */
    public static void checkExcelVaild(File file) throws Exception {
        String message = "该文件是EXCEL文件！";
        if (!file.exists()) {
            message = "文件不存在！";
            throw new Exception(message);
        }
        if (!file.isFile() || ((!file.getName().endsWith(EXCEL_XLS) && !file.getName().endsWith(EXCEL_XLSX)))) {
            System.out.println(file.isFile() + "===" + file.getName().endsWith(EXCEL_XLS) + "==="
                    + file.getName().endsWith(EXCEL_XLSX));
            System.out.println(file.getName());
            message = "文件不是Excel";
            throw new Exception(message);
        }
    }

    /**
     * 判断字符数组是否全为空
     *
     * @param strList
     * @return
     */
    public static boolean checkRowNull(List<String> strList) {
        for (String str : strList) {
            if (str != null && !"".equals(str.trim())) {
                return true;
            }
        }
        return false;
    }
}