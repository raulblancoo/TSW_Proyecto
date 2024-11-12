package com.tsw.ComPay.Dto;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String avatarURL;
}
