package top.hfer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/login-success")
    public String loginSuccess() {
        return "登录成功";
    }

}
