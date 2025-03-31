package org.example.backend.Controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.example.backend.Entity.pojp.RestBean;
import org.example.backend.Entity.pojp.User;
import org.example.backend.Service.UserService;
import org.example.backend.utils.JWTUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")

public class AuthController {
    @Resource
    UserService userService;

    //get post两种请求方法
@PostMapping("login")
    public RestBean<String> login(@RequestParam String username, @RequestParam String password, HttpSession session){
    User loginUser=userService.login(username,password);
    if (loginUser == null){
        return RestBean.failure(401,"账号密码错误");
    }
    else {
        return RestBean.success("登录成功", JWTUtil.createToken(loginUser));
    }
}

}
