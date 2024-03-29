package Mx.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author Meng Xin
 * @Date 2020/8/2 14:38
 */
@Getter
@Setter
@ToString
public class Response {
    private boolean success;//业务是否处理成功
    private String code;//错误码
    private String message;//错误消息
    private Object data;//业务字段
    private String stackTrace;//出现异常时，堆栈信息
}
