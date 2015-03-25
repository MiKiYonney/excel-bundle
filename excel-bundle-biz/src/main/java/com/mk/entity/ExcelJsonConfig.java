package com.mk.entity;


import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yonney.yang
 * Date: 14-11-19
 * Time: 下午5:51
 * To change this template use File | Settings | File Templates.
 */
public class ExcelJsonConfig extends BaseEntity {
    private Map<String,Object> config;

    private String tableName;
    private Map<String,String> configCondition;
    private List<Map<String, Object>> definition;

    public ExcelJsonConfig(String configKey){
        //this.config = ReadFileUtils.getJsonMap(configKey);

    }

    public ExcelJsonConfig(){

    }

    public String getTableName() {
        this.tableName = (String)config.get("tableName");
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, String> getConfigCondition() {
        return (Map<String,String>)config.get("condition");
    }

    public void setConfigCondition(Map<String, String> configCondition) {
        this.configCondition = configCondition;
    }

    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }

    public void setDefinition(List<Map<String, Object>> definition) {
        this.definition = definition;
    }

    public List<Map<String, Object>> getDefinition() {
        return (List<Map<String, Object>>)config.get("definition");
    }

}