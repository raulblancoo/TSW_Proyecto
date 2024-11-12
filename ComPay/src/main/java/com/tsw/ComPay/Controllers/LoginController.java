package com.tsw.ComPay.Controllers;


import com.tsw.ComPay.Dto.LoginDto;
import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new LoginDto());
        return "login/login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("login", new LoginDto());
        return "login/login";
    }

    @PostMapping("/register")
    public String registerPost(Model model, @ModelAttribute("user") UserDto userDto) {
        String avatarURL = "https://ui-avatars.com/api?name=" + userDto.getName() + "+" + userDto.getSurname();
        userDto.setAvatarURL(avatarURL);
        model.addAttribute("user", userDto);
        userService.saveUser(userDto);
        return "login/login";
    }
}
