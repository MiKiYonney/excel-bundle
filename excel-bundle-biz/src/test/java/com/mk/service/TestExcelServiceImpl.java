package com.mk.service;

import com.mk.entity.ExcelInfo;
import com.mk.enums.ReportEnum;
import com.mk.test.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yonney.yang on 2015/3/26.
 */

public class TestExcelServiceImpl extends AbstractTest {
    @Autowired
    private  ExcelService excelService;

    ExcelInfo excelInfo;

    @Test
    public void testGetExcelInfo(){
        Map<String,Object> condition = new HashMap<String, Object>();
        condition.put("classId",1);
        condition.put("hp_cal_dt","2015-3");
        excelInfo = excelService.getExcelInfo(ReportEnum.StuInfo,condition);
    }

    @Test
    public void testExportExcel(){
        try {
            excelService.exportExcel(excelInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
