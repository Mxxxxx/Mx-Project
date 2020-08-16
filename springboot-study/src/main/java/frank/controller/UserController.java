package frank.controller;

import frank.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Meng Xin
 * @Date 2020/8/12 21:08
 */

@Controller//当前类型注册到实例中，并指定位web请求的处理
@RequestMapping("/user") //使用在类、方法上。定义请求相关配置：路径、请求方法等
public class UserController {
    //@Autowired//装配
    /*两个注解都可以装配Bean，但有什么区别？
    @Autowired：属于Spring框架，
    @Resource ：属于JDK提供的注解，表示资源，JDK只提供了规范，没有提供实现
    * */
    @Autowired
    @Qualifier("test1")
    private Map<String, String> test;

    @Resource(name = "user1")
    private User user;
    /*
    如果bean和变量名称不一致，需要手动指定
    1. @Autowired
       @Qualifier("bean名称")
    2.@Resource(name = "beab名称")
     */


    @RequestMapping("/login")
    @ResponseBody//返回一个 application/json 的数据类型，返回值会序列化为 json字符串
    public Object login(User user, HttpServletRequest req) {
        //  return test;

        if (!"abc".equals(user.getUsername())) {
            throw new RuntimeException("用户登录失败");
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        return user;
    }

    @RequestMapping("/m")
    public String m() {
        return "/main.html";
    }

    @RequestMapping("/l1")
    public String l1() {
        //不带 / 以当前请求路径为相对位置,
        // 如果返回路径带 /  会去掉当前这个路径，以项目的部署路径为相对位置
        //return "forward:login";//以当前请求路径/user/l1转发到/user/login
        return "forward:/user/login";//以当前带 / 以项目路径查找login
    }

    @RequestMapping("/l2")
    public String l2() {
        return "redirect:/login";
    }


    @RequestMapping("/test/{key}")//路径中使用 变量占位符
    @ResponseBody
    public Object test1(@PathVariable("key") Integer k) {
        System.out.println("==============" + test.get(k));
        return test;
    }

    //请求 GET /user/test2?k1=v1&k2=v2&k3=v3
    @RequestMapping(value = "/test2", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    //@RequestParam    获取url请求中的请求数据，可获取请求体中
    public Object test2(@RequestParam("k1") String k1, //写全
                        @RequestParam String k2,
                        String k3
    ) {//省略注解，默认以变量名为key 查找
        System.out.println("==============" + k1 + "=" + k2 + "=" + k3);
        return test;
    }

    @RequestMapping(value = "test3", method = {RequestMethod.GET, RequestMethod.POST})//默认方法get
    @ResponseBody
    public Object test3(User user) {//请求数据自动映射到参数类型的属性中：username=xxx&password=xxx
        System.out.println(user);
        return test;
    }

    @RequestMapping(value = "test4")//默认方法get
    @ResponseBody
    public Object test4(User user) {
        return null;//响应体为空,配置controler不会进入重写响应体方法
    }

    @RequestMapping(value = "test5")//默认方法get
    @ResponseBody
    public Object test5(User user) {
        return "ok";//返回字符串，响应体的数据格式不再是是json格式，而实字符串
    }

    @RequestMapping("test6")
    @ResponseBody
    //http请求基于Servlet，Spring已经生成了res，req，可以直接在参数中使用
    public Object test6(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("======" + req.getParameter("username") + "====" + req.getParameter("password"));

        return null;
    }

    @RequestMapping(value = "test7")//默认方法get
    @ResponseBody
    public Object test7(@RequestBody User user) {//解析请求数据类型为 application/json时，解析请求体的json字符串为java对象
        System.out.println("=====" + user);
        return null;//响应体为空
    }


    @RequestMapping(value = "test8")//默认方法get
    @ResponseBody
    public Object test8() {
        throw new RuntimeException("错");
    }
}
