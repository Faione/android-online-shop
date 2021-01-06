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

    /**
     * 注册
     * @param user
     * @return
     */
    public ServerResponse registerLogic(User user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public ServerResponse updateUserInfoLogic(User user);
}
