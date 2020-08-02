package Mx.servlet;

import Mx.dao.UserDAO;
import Mx.model.User;
import Mx.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author Meng Xin
 * @Date 2020/8/2 18:09
 */
@WebServlet("/user/login")
public class LoginSrevlet extends AbstractBaseServlet {

    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = JSONUtil.read(req.getInputStream(), User.class);
        User query = UserDAO.query(user);
        if (query == null)
            throw new RuntimeException("用户名密码错误");
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("user", query);
        return null;
    }
}
