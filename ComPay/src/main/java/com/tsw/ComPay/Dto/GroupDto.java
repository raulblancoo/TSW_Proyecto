package com.tsw.ComPay.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class GroupDto {
    private long id;
    private String groupName;
    private String currency;
}
