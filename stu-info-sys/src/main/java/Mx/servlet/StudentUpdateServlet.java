package Mx.servlet;

import Mx.dao.StudentDAO;
import Mx.model.Student;
import Mx.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Meng Xin
 * @Date 2020/8/2 10:59
 */
@WebServlet("/student/update")
public class StudentUpdateServlet extends AbstractBaseServlet {
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Student s = JSONUtil.read(req.getInputStream(), Student.class);
        StudentDAO.update(s);
        return null;
    }
}
