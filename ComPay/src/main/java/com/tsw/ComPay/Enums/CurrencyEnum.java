package com.tsw.ComPay.Enums;

public enum CurrencyEnum {
    EURO("EURO"),
    DOLAR("DOLAR");

    private final String code;

    CurrencyEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
