package com.mk.service.impl;

import com.mk.entity.ExcelInfo;
import com.mk.enums.ReportEnum;
import com.mk.service.ExcelService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yonney.yang
 * Date: 14-11-21
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
public class ExcelServiceImpl implements ExcelService {
    @Override
    public ExcelInfo getExcelInfo(ReportEnum excelConfigEnum, Map<String, Object> conditionMap) {
        return null;
    }

    @Override
    public InputStream exportExcel(ExcelInfo excelInfo) throws IOException {
        return null;
    }
}
