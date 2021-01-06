package stu.swufe.fhl.demo.common;

public enum ResponseCode {
    PARAM_EMPTY(1,"参数不能为空"),
    USERNAME_EMPTY(2,"用户名不能为空"),
    PASSWORD_EMPTY(3,"密码不能为空"),
    EMAIL_EMPTY(4, "邮箱不能为空"),
    PHONE_EMPTY(7,"电话号码不能为空"),
    QUESTION_EMPTY(8, "问题不能为空"),
    ANSWER_EMPTY(9,"密保问题不能为空"),

    USERNAME_NOT_EXIST(5,"用户名不存在"),
    NEED_LOGIN(10,"未登录"),
    USERNAME_EXIST(12,"用户名已经存在"),
    EMAIL_EXIST(13, "邮箱已经存在"),
    PASSWORD_ERROR(6,"密码错误"),
    REGISTER_FAILURE(11,"注册失败"),
    UPDATE_FAILURE(14, "修改失败")
    ;

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResponseCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
