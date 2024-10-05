package com.tsw.ComPay.Controllers;


import com.tsw.ComPay.Dto.LoginDto;
import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.AttributedString;

@Controller
public class LoginController {

    private UserService userService;

    @PostMapping("/register")
    private void register(Model model, UserDto userDto) {
        model.addAttribute("user", userDto);
        userService.saveUser(userDto);
    }

    @PostMapping("/login")
    private void login(Model model) {

        model.addAttribute("login", new LoginDto());
    }
}
