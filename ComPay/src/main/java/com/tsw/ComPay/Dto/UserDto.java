package com.tsw.ComPay.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;


}
