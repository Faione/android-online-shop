package stu.swufe.fhl.demo.service;

import stu.swufe.fhl.demo.model.pojo.User;
import stu.swufe.fhl.demo.utils.ServerResponse;

public interface IUserService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public ServerResponse loginLogic(String username, String password);

    public ServerResponse registerLogic(User user);
}
