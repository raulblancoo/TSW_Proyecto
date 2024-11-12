package com.tsw.ComPay.Dto;

import lombok.Data;

@Data
public class GroupDto {
    private Long id;
    private String groupName;
    private String currency;
    private double amount;
    private String imgURL;
}
