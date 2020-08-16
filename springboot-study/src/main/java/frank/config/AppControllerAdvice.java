package frank.config;

import frank.Model.ResponseResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author Meng Xin
 * @Date 2020/8/16 12:18
 */
/*
异常：1.自定义异常（可以抛出自己写的错误信息）
2.JDK和框架：程序代码运行时抛出
自定义异常：
web项目中所起的作用：
 */
@ControllerAdvice//拦截controller中web的请求类
public class AppControllerAdvice implements ResponseBodyAdvice<Object> {
    @ExceptionHandler({Exception.class})
    //指定处理方法中抛出的异
    public Object handler(Exception e) {
        e.printStackTrace();
        return null;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;//执行Controller中的web请求方法时，方法结束，返回数据到前端时，是否要重写响应体
    }

    @Override
    public ResponseResult beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
       //如果存在字符串情况，需要返回字符串类型，否则会报错
        //判断对象类型，字符串返回objectmapper序列化后的字符串，否则返回统一封装类型
        ResponseResult r = new ResponseResult();
        r.setSuccess(true);
        r.setData(body);
        return r;
    }
}
