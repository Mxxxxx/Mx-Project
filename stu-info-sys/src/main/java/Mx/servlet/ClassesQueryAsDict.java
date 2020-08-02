package Mx.servlet;

import Mx.dao.ClassesDAO;
import Mx.model.Classes;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author Meng Xin
 * @Date 2020/8/2 10:03
 */
@WebServlet("/classes/queryAsDict")
public class ClassesQueryAsDict extends AbstractBaseServlet {

    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Classes> list = ClassesDAO.queryAsDict();
        return list;
    }
}
