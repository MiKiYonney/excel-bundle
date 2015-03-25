package com.mk.service;


import com.mk.entity.ExcelInfo;
import com.mk.enums.ReportEnum;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yonney.yang
 * Date: 14-11-21
 * Time: 下午2:23
 * To change this template use File | Settings | File Templates.
 */
public interface ExcelService {
    public ExcelInfo getExcelInfo(ReportEnum excelConfigEnum, Map<String, Object> conditionMap);

    public InputStream exportExcel(ExcelInfo excelInfo) throws IOException;
}
