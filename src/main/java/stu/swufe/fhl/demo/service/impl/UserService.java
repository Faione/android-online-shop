package stu.swufe.fhl.demo.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stu.swufe.fhl.demo.common.Const;
import stu.swufe.fhl.demo.common.ResponseCode;
import stu.swufe.fhl.demo.dao.UserMapper;
import stu.swufe.fhl.demo.model.pojo.User;
import stu.swufe.fhl.demo.model.vo.UserVO;
import stu.swufe.fhl.demo.service.IUserService;
import stu.swufe.fhl.demo.utils.DateUtil;
import stu.swufe.fhl.demo.utils.MD5Util;
import stu.swufe.fhl.demo.utils.ServerResponse;


@Service
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;

    private UserVO convertToVO(User user){
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setEmail(user.getEmail());
        userVO.setPhone(user.getPhone());
        userVO.setCreateTime(DateUtil.DateToString(user.getCreateTime()));
        userVO.setUpdateTime(DateUtil.DateToString(user.getUpdateTime()));
        return userVO;
    }

    @Override
    public ServerResponse loginLogic(String username, String password) {
        Integer username_exist;
        User user;

        //1. 用户名、密码非空判断
        if(StringUtils.isBlank(username)){
            return ServerResponse.createServerResponseByFailure(ResponseCode.USERNAME_EMPTY.getCode(), ResponseCode.USERNAME_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(password)){
            return ServerResponse.createServerResponseByFailure(ResponseCode.PASSWORD_EMPTY.getCode(), ResponseCode.PASSWORD_EMPTY.getMsg());
        }

        //2. 用户名是否存在
        username_exist = userMapper.findByUsername(username);

        if(username_exist==0){
            return ServerResponse.createServerResponseByFailure(ResponseCode.USERNAME_NOT_EXIST.getCode(), ResponseCode.USERNAME_NOT_EXIST.getMsg());
        }

        //3. 用户名、密码是否与数据库相符
        user = userMapper.findByUsernameAndPassword(username, MD5Util.md5(password));
        if(user==null){
            return ServerResponse.createServerResponseByFailure(ResponseCode.PASSWORD_ERROR.getCode(), ResponseCode.PASSWORD_ERROR.getMsg());
        }

        //4. 查询成功，返回用户信息
        return ServerResponse.createServerResponseBySuccess(convertToVO(user));
    }

    @Override
    public ServerResponse registerLogic(User user) {
        //1. 参数非空校验
        if(user==null){
            return ServerResponse.createServerResponseByFailure(ResponseCode.PARAM_EMPTY.getCode(), ResponseCode.PARAM_EMPTY.getMsg());

        }
        String username=user.getUsername();
        String password=user.getPassword();
        String email=user.getEmail();
        String phone=user.getPhone();
        String question=user.getQuestion();
        String answer=user.getAnswer();
        Integer username_exist, email_exist, insert_result;

         user.setRole(Const.NORMAL_USER);
        //1. 参数非空判断
        if(StringUtils.isBlank(username)){
            return ServerResponse.createServerResponseByFailure(ResponseCode.USERNAME_EMPTY.getCode(), ResponseCode.USERNAME_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(password)){
            return ServerResponse.createServerResponseByFailure(ResponseCode.PASSWORD_EMPTY.getCode(), ResponseCode.PASSWORD_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(email)){
            return ServerResponse.createServerResponseByFailure(ResponseCode.EMAIL_EMPTY.getCode(), ResponseCode.EMAIL_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(phone)){
            return ServerResponse.createServerResponseByFailure(ResponseCode.PHONE_EMPTY.getCode(), ResponseCode.PHONE_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(question)){
            return ServerResponse.createServerResponseByFailure(ResponseCode.QUESTION_EMPTY.getCode(), ResponseCode.QUESTION_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(answer)){
            return ServerResponse.createServerResponseByFailure(ResponseCode.ANSWER_EMPTY.getCode(), ResponseCode.ANSWER_EMPTY.getMsg());
        }

        //2. 用户名重复检测
        username_exist = userMapper.findByUsername(username);

        if(username_exist>0){
            return ServerResponse.createServerResponseByFailure(ResponseCode.USERNAME_EXIST.getCode(), ResponseCode.USERNAME_EXIST.getMsg());
        }

        //3. 邮箱是否存在
        email_exist = userMapper.findByEmail(email);

        if(email_exist>0){
            return ServerResponse.createServerResponseByFailure(ResponseCode.EMAIL_EXIST.getCode(), ResponseCode.EMAIL_EXIST.getMsg());
        }

        //4. 对密码进行MD5加密
        user.setPassword(MD5Util.md5(password));

        //5. 完成注册
        insert_result = userMapper.insert(user);
        if(insert_result==0){
            return ServerResponse.createServerResponseByFailure(ResponseCode.REGISTER_FAILURE.getCode(), ResponseCode.REGISTER_FAILURE.getMsg());
        }

        return ServerResponse.createServerResponseBySuccess();
    }

    @Override
    public ServerResponse updateUserInfoLogic(User user) {
        int sql_check;
        // 1.判断输入参数是否为空
        if(user==null){
            return ServerResponse.createServerResponseByFailure(ResponseCode.PARAM_EMPTY.getCode(), ResponseCode.PARAM_EMPTY.getMsg());
        }

        // 2.判断是否更新成功
        sql_check = userMapper.updateByPrimaryKeySelective(user);

        if(sql_check==0){
            return ServerResponse.createServerResponseByFailure(ResponseCode.UPDATE_FAILURE.getCode(), ResponseCode.UPDATE_FAILURE.getMsg());
        }

        // 3.获得最新的用户信息，并向前端返回
        User updatedUser = userMapper.selectByPrimaryKey(user.getId());

        UserVO userVO = convertToVO(updatedUser);

        return ServerResponse.createServerResponseBySuccess(userVO);
    }
}
