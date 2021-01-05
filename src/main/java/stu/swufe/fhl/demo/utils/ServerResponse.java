package stu.swufe.fhl.demo.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

/**
 * 返回给前端的统一类
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> {

    private int status; // 0：状态调用成功

    private T data; // status=0，将返回数据封装到data中

    private String msg; //提示信息

    private ServerResponse(int status){}
    private ServerResponse(int status, T data){
            this.status = status;
            this.data = data;
    }
    private ServerResponse(int status, T data, String msg){
        this.status = status;
        this.data = data;
        this.msg = msg;
    }
// Success
    public static ServerResponse createServerResponseBySuccess(){
        return new ServerResponse(0);
    }

    public static <T>ServerResponse createServerResponseBySuccess(T data){
        return new ServerResponse(0,data);
    }

    public static <T>ServerResponse createServerResponseBySuccess(T data, String msg){
        return new ServerResponse(0, data=null, msg);
    }
//Failure
    public static <T>ServerResponse createServerResponseByFailure(int status, String msg){
        return new ServerResponse(status, msg);
    }

    @JsonIgnore
    public boolean isSuccess(){
        return this.status == 0;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
