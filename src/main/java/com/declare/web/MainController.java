package com.declare.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/user")
public class MainController {

    @RequestMapping("/")
    @ResponseBody
    public String success() {
        return "success";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // 返回登录页面的视图名，对应login.ftl
    }
}
