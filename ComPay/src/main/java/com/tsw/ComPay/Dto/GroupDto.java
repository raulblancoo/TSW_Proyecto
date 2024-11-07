package com.tsw.ComPay.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class GroupDto {
    private Long id;
    private String groupName;
    private String currency;
    private float amount;
    private String imgURL;
}
