package com.mk.enums;

public enum ReportEnum {
    StuInfo(1, "StuInfo");
    private int typeId;
    private String name;

    ReportEnum(int typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }
}
