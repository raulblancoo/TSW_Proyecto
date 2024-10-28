package com.tsw.ComPay.Controllers;


import com.tsw.ComPay.Dto.LoginDto;
import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        return "index";
    }

    @PostMapping("/register")
    public String registerPost(Model model, @ModelAttribute("user") UserDto userDto) {
        String avatarURL = "https://ui-avatars.com/api?name=" + userDto.getName() + "+" + userDto.getSurname();
        userDto.setAvatarURL(avatarURL);
        model.addAttribute("user", userDto);
        userService.saveUser(userDto);
        return "index";
    }

    /*
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new LoginDto());
        return "index";
    }*/

    @PostMapping("/login")
    public String loginPost(Model model, @ModelAttribute("login") LoginDto loginDto) {
        model.addAttribute("login", loginDto);
        return userService.findByEmailPassword(loginDto.getEmail(), loginDto.getPassword()) != null ? "redirect:/groups" : "index";
    }
}
