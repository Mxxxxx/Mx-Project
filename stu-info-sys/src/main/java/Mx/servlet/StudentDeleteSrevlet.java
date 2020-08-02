package Mx.servlet;

import Mx.dao.StudentDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Meng Xin
 * @Date 2020/8/2 17:17
 */
@WebServlet("/student/delete")
public class StudentDeleteSrevlet extends AbstractBaseServlet {

    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] ids = req.getParameterValues("ids");
        StudentDAO.delete(ids);
        return null;
    }
}
