package com.tsw.ComPay.Dto;

public interface PruebaDto {
    Long getExpense_id();
    double getAmount();
    Long getOriginUserId();
    Long getDestinyUserId();
    double getDebt();
    Long getGroupId();
}