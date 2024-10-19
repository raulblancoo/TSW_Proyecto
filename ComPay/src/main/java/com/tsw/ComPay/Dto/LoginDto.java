package com.tsw.ComPay.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    private String password;
    private String email;

    public String getPassword() { return this.password; }

    public String getEmail() { return this.email; }

    public void setPassword(String password) { this.password = password; }

    public void setEmail(String email) { this.email = email; }
}
