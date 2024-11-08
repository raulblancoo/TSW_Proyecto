package com.tsw.ComPay.Enums;

public enum ExpenseMethodEnum {
    PARTESIGUALES("PARTESIGUALES"),
    PARTESDESIGUALES("PARTESDESIGUALES"),
    PORCENTAJES("PORCENTAJES");

    private final String code;

    ExpenseMethodEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
