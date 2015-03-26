package com.mk.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: yonney.yang
 * Date: 15-03-26
 */
public enum GenderEnum {
    Male("男生", 0),
    FeMale("女生", 1);

    private String desc;
    private int code;
    private GenderEnum(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    private static final String CODE = "value";
    private static final String DESC = "text";

    public static GenderEnum getByCode(int code) {
        for (GenderEnum enumItem : values())
            if (enumItem.getCode() == code)
                return enumItem;
        return null;
    }

    public static List<Map<String,Object>> toList(){
        List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
        for(GenderEnum enumItem: values()){
            Map<String,Object> enumInfo = new HashMap<String, Object>();
            enumInfo.put(CODE,enumItem.code);
            enumInfo.put(DESC,enumItem.desc);
            resultList.add(enumInfo);
        }
        return resultList;
    }

    public  static List<String> getAllDesc(){
        List<String> resultList = new ArrayList<String>();
        for(GenderEnum enumItem: values()){
            resultList.add(enumItem.getDesc());
        }
        return resultList;
    }

    public  static List<Integer> getAllCode(){
        List<Integer> resultList = new ArrayList<Integer>();
        for(GenderEnum enumItem: values()){
            resultList.add(enumItem.getCode());
        }
        return resultList;
    }

    public static Integer getCodeByDesc(String desc){
        for(GenderEnum enumItem: values()){
            if(enumItem.getDesc().equals(desc)){
                return enumItem.getCode();
            }
        }
        return null;
    }

    public static String getDescByCode(int code){
        for(GenderEnum enumItem: values()){
            if(enumItem.getCode() == code){
                return enumItem.getDesc();
            }
        }
        return null;
    }
}
