package com.tsw.ComPay.Dto;

import lombok.Data;

@Data
public class NewGroupMemberDto {
    private int groupId;
    private String[] emails;
}
