package stu.swufe.fhl.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stu.swufe.fhl.demo.common.Const;
import stu.swufe.fhl.demo.common.ResponseCode;
import stu.swufe.fhl.demo.model.pojo.User;
import stu.swufe.fhl.demo.model.vo.UserVO;
import stu.swufe.fhl.demo.service.IUserService;
import stu.swufe.fhl.demo.utils.ServerResponse;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/portal/") // portal: 前端
public class UserController {


    @Autowired
    IUserService userService;


    @RequestMapping(value = "user/login.do")
    public ServerResponse login(String username, String password, HttpSession session){
        ServerResponse serverResponse = userService.loginLogic(username, password);

        // 判断用户是否登录成功，若成功，用session记录用户登录信息
        if (serverResponse.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, serverResponse.getData());
        }

        return serverResponse;
    }


    @RequestMapping(value = "user/register.do")
    public ServerResponse register(User user){
        return userService.registerLogic(user);
    }


    @RequestMapping(value = "user/updateInfo.do")
    public ServerResponse updateUser(User user, HttpSession session){
        // 判断用户是否登录
        UserVO userInfo = (UserVO) session.getAttribute(Const.CURRENT_USER);

        if(userInfo==null){
            return ServerResponse.createServerResponseByFailure(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }

        // 交给Service进行更新
        user.setId(userInfo.getId());  // 当前登录的用户有默认的ID，显然此不会作为用户输入之一，因而必须要从当前登录的UserVO 对象中获得

        ServerResponse serverResponse = userService.updateUserInfoLogic(user);

        // 更新session
        session.setAttribute(Const.CURRENT_USER, serverResponse.getData());

        return serverResponse;
    }
}
