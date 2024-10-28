package com.tsw.ComPay.Controllers;


import com.tsw.ComPay.Dto.LoginDto;
import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new LoginDto());
        return "login/login";
    }

    @PostMapping("/register")
    public String registerPost(Model model, @ModelAttribute("user") UserDto userDto) {
        model.addAttribute("user", userDto);
        userService.saveUser(userDto);
        return "index";
    }

}
