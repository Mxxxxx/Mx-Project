package frank.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import frank.Model.User;
import frank.config.interceptor.Logininterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Meng Xin
 * @Date 2020/8/12 21:21
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {//web框架执行初始化工作时，调用该接口
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 拦截器
     * <p>
     * /* ：代表一级路径，如 /user/* 可以匹配到 user/abc ，不能匹配到user/abc/a
     * /**：代表多级路径  /**表示拦截模糊匹配   静态资源也会被拦截
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {//
        //实现一个用户会话管理功能
        registry.addInterceptor(new Logininterceptor(objectMapper))//链式方法调用：当前类型的方法返回值还是时this
                .addPathPatterns("/user/**")//添加要拦截的路径
                .excludePathPatterns("/user/login");//排除的路径
    }

    @Bean
    //以返回值做为bean 注册到容器中
    public Map<String, String> test1() {
        Map<String, String> map = new HashMap<>();
        map.put("nih", "a");
        map.put("as", "sasa");
        map.put("saa", "sas");
        return map;
    }

    @Bean
    //以返回值做为bean 注册到容器中
    public Map<Integer, Integer> test2() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        return map;
    }

    @Bean
    public User user1() {
        User u = new User();
        u.setUsername("小红");
        u.setPassword("199846");
        return u;
    }

    @Bean
    public User user2() {
        User u = new User();
        u.setUsername("小名");
        u.setPassword("198463");
        return u;
    }
}
