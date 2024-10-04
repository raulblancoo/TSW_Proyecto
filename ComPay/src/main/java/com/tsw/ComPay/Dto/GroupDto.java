package com.tsw.ComPay.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupDto {
    private long id;
    private String groupname;
}
