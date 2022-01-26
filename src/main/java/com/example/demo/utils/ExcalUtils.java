package com.example.demo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcalUtils {

    /**
     * 导出 Excel
     *
     * @param columnTitleList 需要导出的列的标题
     * @param columnNameList  需要导出的列的名称
     * @param dataList        导出的数据
     * @return
     */
    public static Workbook exportExcel(List<String> columnTitleList, List<String> columnNameList, List<Map<String, Object>> dataList) {
        try {
            // 创建 Excel
            Workbook excelWorkbook = new HSSFWorkbook();
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 创建 Sheet
            Sheet sheet1 = excelWorkbook.createSheet("sheet1");
            CellRangeAddress c = CellRangeAddress.valueOf("A2:K2");
            sheet1.setAutoFilter(c);

            // 创建首行
            Row firstRow = sheet1.createRow(0);
            sheet1.addMergedRegion(new CellRangeAddress(0,0,0,columnNameList.size()));

            //CellStyle cellStyle = excelWorkbook.createCellStyle();
            // 表头标题样式
            Font headfont = excelWorkbook.createFont();
            headfont.setFontName("宋体");
            headfont.setFontHeightInPoints((short) 20);// 字体大小
            CellStyle headstyle = excelWorkbook.createCellStyle();
            headstyle.setFont(headfont);
            headstyle.setAlignment(HorizontalAlignment.CENTER);// 左右居中
            headstyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
            headstyle.setLocked(true);
            Cell cell1 = firstRow.createCell((short) 0);
            cell1.setCellStyle(headstyle);
            cell1.setCellValue(columnTitleList.get(0));
            // 创建首行
            Row secondRow = sheet1.createRow(1);
            // 表头标题样式
            Font secondfont = excelWorkbook.createFont();
            secondfont.setFontName("黑体");
            secondfont.setFontHeightInPoints((short)15);// 字体大小
            CellStyle secondstyle = excelWorkbook.createCellStyle();
            secondstyle.setFont(secondfont);
            secondstyle.setAlignment(HorizontalAlignment.CENTER);// 左右居中
            secondstyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
            secondstyle.setLocked(true);


            for (int k = 0; k < columnNameList.size(); k++) {
                String columnName = columnNameList.get(k);
                Cell cell2 = secondRow.createCell((short) k);
                // 创建单元格并给单元格设置值
                sheet1.setColumnWidth((short)k,(short)4000);
                cell2.setCellStyle(secondstyle);
                cell2.setCellValue(columnName);
            }
            ObjectMapper mapper = new ObjectMapper();

            Font contextfont = excelWorkbook.createFont();
            contextfont.setFontName("宋体");
            contextfont.setFontHeightInPoints((short)12);// 字体大小
            CellStyle contextstyle = excelWorkbook.createCellStyle();
            contextstyle.setFont(contextfont);
            contextstyle.setAlignment(HorizontalAlignment.CENTER);// 左右居中
            contextstyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
            contextstyle.setLocked(true);
            // 循环查询到的数据，生成 Excel
            for (int i = 0; i < dataList.size(); i++) {
                Map<String, Object> dataMap = dataList.get(i);
                // 创建数据行
                Row dataRow = sheet1.createRow(i + 2);
                for (int j = 0; j < columnNameList.size(); j++) {
                    String columnName = columnNameList.get(j);

                    Object value = dataMap.get(columnName);
                    // 创建单元格并给单元格设置值
                    Cell cell = dataRow.createCell(j);
                    if (value != null) {
                        if (value instanceof Date) {
                            cell.setCellValue(df.format((Date) value));
                            cell.setCellStyle(contextstyle);
                        } else {
                            cell.setCellStyle(contextstyle);
                            cell.setCellValue(value.toString());
                        }
                    }
                }
            }
            return excelWorkbook;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // UTF-8 编码
    public static String toUTF8(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    // 解决excel类型问题，获得数值
    @SuppressWarnings("deprecation")
    public static String getValue(Cell cell) {
        String value = "";
        if (null == cell) {
            return value;
        }
        switch (cell.getCellType()) {
            // 数值型
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是date类型则 ，获取该cell的date值
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = format.format(date);
                } else {// 纯数字
                    BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                    value = big.toString();
                    // 解决1234.0 去掉后面的.0
                    if (null != value && !"".equals(value.trim())) {
                        String[] item = value.split("[.]");
                        if (1 < item.length && "0".equals(item[1])) {
                            value = item[0];
                        }
                    }
                }
                break;
            // 字符串类型
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue().toString();
                break;
            // 公式类型
            case Cell.CELL_TYPE_FORMULA:
                // 读公式计算值
                value = String.valueOf(cell.getNumericCellValue());
                if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                    value = cell.getStringCellValue().toString();
                }
                break;
            // 布尔类型
            case Cell.CELL_TYPE_BOOLEAN:
                value = " " + cell.getBooleanCellValue();
                break;
            // 空值
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            // 故障
            case Cell.CELL_TYPE_ERROR:
                value = "";
                break;
            default:
                value = cell.getStringCellValue().toString();
        }
        if ("null".endsWith(value.trim())) {
            value = "";
        }
        return value;
    }

    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
