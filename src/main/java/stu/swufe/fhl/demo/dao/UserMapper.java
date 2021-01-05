package stu.swufe.fhl.demo.dao;

import org.apache.ibatis.annotations.Param;
import stu.swufe.fhl.demo.model.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 判断用户名称是否存在
     *
     * @param username
     * @return
     */
    Integer findByUsername(@Param("username") String username);

    /**
     * 判断用户是否存在
     *
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 判断邮箱是否存在
     *
     * @param email
     * @return
     */
    Integer findByEmail(@Param("email") String email);
}