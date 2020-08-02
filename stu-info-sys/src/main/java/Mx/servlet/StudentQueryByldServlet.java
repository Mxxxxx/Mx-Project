package Mx.servlet;

import Mx.dao.StudentDAO;
import Mx.model.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Meng Xin
 * @Date 2020/8/2 10:29
 */
@WebServlet("/student/queryById")
public class StudentQueryByldServlet extends AbstractBaseServlet {

    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        Student s = StudentDAO.queryByid(Integer.parseInt(id));
        return s;
    }
}
