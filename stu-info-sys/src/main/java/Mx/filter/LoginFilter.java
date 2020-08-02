package Mx.filter;

import Mx.model.Response;
import Mx.util.JSONUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author Meng Xin
 * @Date 2020/8/2 22:46
 */
//过滤器
//只要请求路径匹配到过滤器的路径，都会先执行 doFilter方法
//至于是否往后执行，依赖我们是否调用filterchain.doFilter方法
@WebFilter("/*")//可以匹配到所有路径
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //后台接口：校验除登录接口之外的后台接口，没有登录时，不允许访问
        //前端资源：只校验/public/page/main.html
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getServletPath();
        //校验
        if ("/public/page/main.html".equals(uri) || (!uri.startsWith("/public/")
                && !uri.startsWith("/static") && !"/user/login".equals(uri))) {
            HttpSession session = request.getSession(false);//没有session的时候，返回null
            if (session == null) {
                //首页重定向到登录页面，如果后端接口，返回错误的json数据
                if ("/public/page/main.html".equals(uri)) {
                    response.setContentType("text/html");
                    String schema = request.getScheme();//http
                    String host = request.getServerName();//服务器ip
                    int port = request.getServerPort();//端口号
                    String ctx = request.getContextPath();//项目部署路径，应用上下文sis
//                    response.sendRedirect("/public/index.html"); 会出问题
                    String basePath = schema + "://" + host + ":" + port + ctx;
                    response.sendRedirect(basePath + "/public/index.html");
                    return;
                    //请求后端非登录接口：正常未登录的请求返回 401
                } else if ((!uri.startsWith("/public/")
                        && !uri.startsWith("/static") && !"/user/login".equals(uri))) {
                    response.setContentType("application/json");
                    PrintWriter pw = response.getWriter();
                    Response r = new Response();
                    r.setCode("ERR401");
                    r.setMessage("不允许访问");
                    response.setStatus(401);
                    pw.println(JSONUtil.write(r));
                    pw.flush();
                    return;
                }
                ;
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
