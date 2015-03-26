package com.mk.enums;


import com.mk.exceptions.ExcelHandleException;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Excel数据格式化，如对数Percent百分比，<br/>
 * Decimal为取几位小数点，TranslateToText为从枚举取得desc <br/>
 * Created by yonney.yang on 2015/3/26.
 */
public enum CellDataHandlerEnum {
    Percent("Percent") {
        @Override
        Object handle(Object a, Map data, Object... args) {
            if (a == null || "/".equals(a)) {
                return "/";
            }
            if (a instanceof Double) {
                Double d = (Double) a;
                return Math.round(d * 100) + "%";
            }
            return a;
        }
    },
    Decimal("Decimal") {
        @Override
        Object handle(Object a, Map data, Object... args) {
            if (a == null || "/".equals(a)) {
                return "/";
            }
            if (a instanceof Double) {
                if (args != null) {
                    return new BigDecimal((Double) a).setScale(Integer.parseInt((String) args[0]), BigDecimal.ROUND_HALF_UP).doubleValue();
                } else {
                    return new BigDecimal((Double) a).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }
            }
            return a;
        }
    },
    TranslateToText("TranslateToText") {
        @Override
        Object handle(Object a, Map data, Object... args) {
            if (a == null || "/".equals(a)) {
                return "/";
            }
            try {
                String enumName = "com.mk.enums." + ((String) args[0]).trim();
                Method getDescByCode = Class.forName(enumName).getMethod("getDescByCode", int.class);
                return getDescByCode.invoke(Class.forName(enumName), a);
            } catch (Exception e) {
                throw new ExcelHandleException("TranslateToText enum not found");
            }
        }
    };
    private String desc;

    CellDataHandlerEnum(String desc) {
        this.desc = desc;
    }

    public static CellDataHandlerEnum getDataHandlerEnumByDesc(String desc) {
        for (CellDataHandlerEnum d : CellDataHandlerEnum.values()) {
            if (d.getDesc().equals(desc)) {
                return d;
            }
        }
        return null;
    }

    abstract Object handle(Object a, Map data, Object... args);

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
