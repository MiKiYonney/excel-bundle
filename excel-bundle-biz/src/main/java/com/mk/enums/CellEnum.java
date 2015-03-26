package com.mk.enums;


import com.mk.utils.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Excel单元格处理
 * DefaultHeader为Excel头部默认处理器
 * DefaultItemCell为Excel内容默认处理器
 * User: yonney.yang
 * Date: 15-03-26
 */
public enum CellEnum {
    DefaultHeader("DefaultHeader") {
        @Override
        public int writeCell(Workbook wb, Row row, Map data, List items, Map definition, int index) {
            Cell cell = row.createCell(index);
            cell.setCellValue((String) definition.get(DESC));
            return index + 1;
        }
    },
    DefaultItemCell("DefaultItemCell") {
        @Override
        public int writeCell(Workbook wb, Row row, Map data, List items, Map definition, int index) {
            Cell cell = row.createCell(index);
            setCellData(wb, cell, getValue(definition, data));
            return index + 1;
        }
    };

    private static String MONEY_FORMAT = "#,##0";
    private static String HANDLERS = "handlers";
    private static String KEY = "key";
    private static String DESC = "desc";

    private String desc;

    CellEnum(String desc) {
        this.desc = desc;
    }

    public static CellEnum getCellEnumByDesc(String desc) {
        for (CellEnum c : CellEnum.values()) {
            if (c.getDesc().equals(desc)) {
                return c;
            }
        }
        return null;
    }

    public abstract int writeCell(Workbook wb, Row row, Map data, List items, Map definition, int index);

    public Object getValue(Map curDefinition, Map data) {
        List<String> handlers = (List<String>) curDefinition.get(HANDLERS);
        Object value = data.get(curDefinition.get(KEY));

        if (CollectionUtils.isNotEmpty(handlers)) {
            String[] args = null;
            for (String h : handlers) {
                if (h.indexOf("|") != -1) {
                    args = h.substring(h.indexOf("|") + 1).split("\\|");
                    h = h.substring(0, h.indexOf("|"));
                }
                CellDataHandlerEnum handlerEnum = CellDataHandlerEnum.getDataHandlerEnumByDesc(h.trim());
                if (handlerEnum != null) {
                    value = handlerEnum.handle(value, data, args);
                }
            }
        }
        return value;
    }

    public void setCellData(Workbook wb, Cell cell, Object data) {
        if (data instanceof Double) {
            cell.setCellValue((Double) data);
        } else if (data instanceof Integer) {
            cell.setCellValue((Integer) data);
        } else if (data instanceof Long) {
            cell.setCellValue((Long) data);
        } else if (data instanceof Float) {
            cell.setCellValue((Float) data);
        } else if (data instanceof Date) {
            cell.setCellValue((Date) data);
        } else if (data instanceof String) {
            cell.setCellValue((String) data);
        } else {
            cell.setCellValue(data.toString());
        }
        if (data instanceof Number) {
            CellStyle cellStyle = wb.createCellStyle();
            DataFormat format = wb.createDataFormat();
            cellStyle.setDataFormat(format.getFormat(MONEY_FORMAT));
            cell.setCellStyle(cellStyle);
        }
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static void main(String[] args) {
        try {
            Workbook wb = new HSSFWorkbook();
            FileOutputStream fileOut = new FileOutputStream("D:\\workbook.xls");
            Cell cell = wb.createSheet().createRow(0).createCell(0);
            cell.setCellValue(22376);

            CellStyle cellStyle = wb.createCellStyle();
            DataFormat format = wb.createDataFormat();
            cellStyle.setDataFormat(format.getFormat(MONEY_FORMAT));
            cell.setCellStyle(cellStyle);

            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
