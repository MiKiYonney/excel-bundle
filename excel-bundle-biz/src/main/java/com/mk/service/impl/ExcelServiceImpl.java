package com.mk.service.impl;

import com.mk.entity.ExcelInfo;
import com.mk.entity.ExcelJsonConfig;
import com.mk.enums.CellEnum;
import com.mk.enums.ReportEnum;
import com.mk.service.ExcelService;
import com.mk.utils.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: yonney.yang
 * Date: 15-03-26
 */
@Component
public class ExcelServiceImpl implements ExcelService {
    @Override
    public ExcelInfo getExcelInfo(ReportEnum excelConfigEnum, Map<String, Object> conditionMap) {
        ExcelJsonConfig excelJsonConfig = new ExcelJsonConfig(excelConfigEnum.name());
        /**
         * 这里可以从数据库取数据，返回的数据以List<HashMap<String, Object>>封装到ExcelInfo中的data值中去
         */
       /* ReportQueryParameterDTO reportQueryParameterDTO = initQueryParameter(excelJsonConfig, conditionMap);
        ReportQueryResultDTO reportQueryResultDTO = reportRemoteService.getReport(reportQueryParameterDTO);
        HintUtils.assertRes(reportQueryResultDTO.isResult(), reportQueryResultDTO.getMessage());*/

        ExcelInfo excelInfo = new ExcelInfo();
        excelInfo.setExcelDefinition(excelJsonConfig.getDefinition());
       /* if(CollectionUtils.isNotEmpty(reportQueryResultDTO.getReport())) {
            excelInfo.setExcelData(reportQueryResultDTO.getReport());
        }else{
            excelInfo.setExcelData(new ArrayList<HashMap<String, Object>>());
        }*/
        excelInfo.setExcelData(new ArrayList<HashMap<String, Object>>());
        return excelInfo;
    }

    @Override
    public InputStream exportExcel(ExcelInfo excelInfo) throws IOException {
        List<Map<String, Object>> excelDefinition = excelInfo.getExcelDefinition();
        List<HashMap<String, Object>> excelData = excelInfo.getExcelData();
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Sheet1");
        wb.createCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);

        int titleIndex = 0;
        Row titleRow = sheet.createRow(0);
        for(int i = 0; i < excelDefinition.size(); i++){
            Map<String, Object> currentDefinition = excelDefinition.get(i);
            Object headerVal = currentDefinition.get("header");
            String header = (headerVal== null ? CellEnum.DefaultHeader.getDesc() : (String)headerVal);
            titleIndex = CellEnum.getCellEnumByDesc(header).writeCell(wb,titleRow,currentDefinition,excelDefinition,currentDefinition,titleIndex);
        }

        if (CollectionUtils.isNotEmpty(excelData)) {
            for (int i = 0; i < excelData.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                int dataIndex = 0;
                for(Map<String,Object> def : excelDefinition){
                    String item = (def.get("item")== null ? CellEnum.DefaultItemCell.getDesc() : (String)def.get("item"));
                    dataIndex = CellEnum.getCellEnumByDesc(item).writeCell(wb,dataRow,excelData.get(i),excelData,def,dataIndex);
                }
            }
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        byte[] b = os.toByteArray();
        return new ByteArrayInputStream(b);
    }

}
