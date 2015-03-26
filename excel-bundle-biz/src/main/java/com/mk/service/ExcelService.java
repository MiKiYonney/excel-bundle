package com.mk.service;


import com.mk.entity.ExcelInfo;
import com.mk.enums.ReportEnum;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Excel数据获取和导出逻辑处理类
 * Created by yonney.yang on 2015/3/26.
 */
public interface ExcelService {
    /**
     * 根据Json配置文件和前台传来的查询条件，读Json配置并取数据，封装ExcelInfo
     * @param excelConfigEnum
     * @param conditionMap
     * @return
     */
    public ExcelInfo getExcelInfo(ReportEnum excelConfigEnum, Map<String, Object> conditionMap);

    /**
     * 导出Excel
     * @param excelInfo
     * @return
     * @throws IOException
     */
    public InputStream exportExcel(ExcelInfo excelInfo) throws IOException;
}
