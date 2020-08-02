package Mx.servlet;

import Mx.model.Response;
import Mx.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Author Meng Xin
 * @Date 2020/8/2 14:35
 */
public abstract class AbstractBaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //后面的接口的，Servlet中都是类似的写法，
        //如果抛出异常怎么处理？
        //怎么封装
        //1
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        //2
        resp.setContentType("application/json");
        //3
        PrintWriter writer = resp.getWriter();
        Response r = new Response();
        //HttpServletRequest对象.getParameter可以获取哪些请求数据？url和请求体，满足k1-va&k2=v2格式的数据
        //一般说表单提交的默认方式：表示的意思是请求的Content-Type字段x-www-form-urlencoded
        //get会怎么样？在url中  post会怎么样？在请求头中，格式：k1-va&k2=v2
        //表单不适用默认的方式时，会怎么样？比如手写前端代码，发送ajax请求，请求为application/json：请求体：jason字符串
        //HttpServletRequest对象.getInputStream() 通过输入流获取，请求体都可以获取到，依赖自己代码解析
        try {
            Object o = process(req, resp);
            r.setSuccess(true);
            r.setCode("COK200");
            r.setMessage("操作成功");
            r.setData(o);

        } catch (Exception e) {
            r.setCode("ERR500");
            r.setMessage(e.getMessage());
            StringWriter sw = new StringWriter();
            PrintWriter write = new PrintWriter(sw);
            e.printStackTrace(write);
            String stackTrace = sw.toString();
            System.err.println(stackTrace);
        }
        writer.println(JSONUtil.write(r));
        writer.flush();
    }

    protected abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
