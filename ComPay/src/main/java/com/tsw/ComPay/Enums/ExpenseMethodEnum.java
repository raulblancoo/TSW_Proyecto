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

    @Override
    public String toString() {
        return this.code.replaceAll("([a-z])([A-Z])", "$1 $2").replaceAll("([a-zA-Z])([0-9])", "$1 $2");
    }
}
