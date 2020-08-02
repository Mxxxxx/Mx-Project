package Mx.servlet;

import Mx.dao.StudentDAO;
import Mx.model.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author Meng Xin
 * @Date 2020/7/31 21:21
 */
@WebServlet("/student/query")
public class StudentServlet extends AbstractBaseServlet {
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Student> students = StudentDAO.query("");
        return students;
    }
}
