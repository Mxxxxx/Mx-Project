package Mx.dao;

import Mx.model.Classes;
import Mx.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Meng Xin
 * @Date 2020/8/2 10:13
 */
public class ClassesDAO {
    public static List<Classes> queryAsDict() {
        //jdbc查询
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet r = null;
        List<Classes> list = new ArrayList<>();
        try {
            //1.获取数据库连接
            c = DBUtil.getConnection();
            //2.创建操作命令对象
            String sql = "select id, classes_name, classes_graduate_year, classes_major from classes";
            ps = c.prepareStatement(sql);
            //4.执行sql语句
            r = ps.executeQuery();
            //4.查询结果集
            while (r.next()) {
                Classes classes = new Classes();
                classes.setDictionaryTagKey(String.valueOf(r.getString("id")));
                classes.setDictionaryTagValue(r.getString("classes_name"));
                classes.setClassesGraduateYear(r.getString("classes_graduate_year"));
                classes.setClassesMajor(r.getString("classes_major"));
                //设置属性，通过结果集获取，并设置
                list.add(classes);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException("查询班级数据字典出错", e);
        } finally {
            DBUtil.close(c, ps, r);
        }
    }
}
