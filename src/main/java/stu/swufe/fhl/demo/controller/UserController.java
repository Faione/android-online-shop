package stu.swufe.fhl.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stu.swufe.fhl.demo.model.pojo.User;
import stu.swufe.fhl.demo.service.IUserService;
import stu.swufe.fhl.demo.utils.ServerResponse;

@RestController
@RequestMapping("/portal/") // portal: 前端
public class UserController {


    @Autowired
    IUserService userService;


    @RequestMapping(value = "user/login.do")
    public ServerResponse login(String username, String password){
        return userService.loginLogic(username, password);
    }

    @RequestMapping(value = "user/register.do")
    public ServerResponse register(User user){
        return userService.registerLogic(user);
    }
}
