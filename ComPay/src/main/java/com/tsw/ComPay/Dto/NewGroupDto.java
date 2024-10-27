package com.tsw.ComPay.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewGroupDto {
    private String groupName;
    private String[] emails;
    private String currency;
    private String imgURL;
    private double amount;
}
