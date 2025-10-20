package com.example.securityweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home() {
        return "home"; // src/main/resources/templates/home.html
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin"; // admin page
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // login.html
    }
}

