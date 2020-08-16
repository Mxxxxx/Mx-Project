package frank.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import frank.Model.ResponseResult;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @Author Meng Xin
 * @Date 2020/8/16 15:12
 */

/**
 * 拦截器：
 * 只有路径配置的拦截路径匹配请求路径的情况，才会执行拦截器的方法
 */
public class Logininterceptor implements HandlerInterceptor {

    private ObjectMapper objectMapper;
    public Logininterceptor(ObjectMapper objectMapper) {
        this.objectMapper = this.objectMapper;
    }

    /*
            Controller中请求方法执行前，就会调用preHandle方法。返回值决定是否继续执行Controller中的方法
            return true; 继续执行Controller的方法
            return false; 不执行后续
             */
    @Override
    //方法执行前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        //登录允许访问
        if (session != null && session.getAttribute("user") != null) {
            return true;
        }
        //没有登录
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ResponseResult r = new ResponseResult();
        r.setCode("ERR401");
        r.setMessage("用户未登录，不允许访问");
        String json = objectMapper.writeValueAsString(r);//将json对象序列化为json字符串
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.flush();
        return false;
    }

    //方法执行完之后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
