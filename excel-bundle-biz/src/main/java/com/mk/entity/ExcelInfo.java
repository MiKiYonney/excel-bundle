package com.mk.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yonney.yang
 * Date: 14-11-19
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 */
public class ExcelInfo {
    private List<Map<String,Object>> excelDefinition;
    private List<HashMap<String, Object>> excelData;

    public List<HashMap<String, Object>> getExcelData() {
        return excelData;
    }

    public void setExcelData(List<HashMap<String, Object>> excelData) {
        this.excelData = excelData;
    }

    public List<Map<String, Object>> getExcelDefinition() {
        return excelDefinition;
    }

    public void setExcelDefinition(List<Map<String, Object>> excelDefinition) {
        this.excelDefinition = excelDefinition;
    }
}
