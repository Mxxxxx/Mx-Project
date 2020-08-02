package Mx.dao;

import Mx.model.Classes;
import Mx.model.Student;
import Mx.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Meng Xin
 * @Date 2020/7/31 21:23
 */
public class StudentDAO {
    public static List<Student> query(String key) {
        //jdbc查询
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet r = null;
        List<Student> list = new ArrayList<>();
        try {
            //1.获取数据库连接
            c = DBUtil.getConnection();
            //2.创建操作命令对象
            String sql = "select s.id,  " +
                    "       S.student_name,  " +
                    "       S.student_no,  " +
                    "       S.id_card,  " +
                    "       S.student_email,  " +
                    "       S.classes_id,  " +
                    "       S.create_time,  " +
                    "       c.id cid,  " +
                    "       c.classes_name,  " +
                    "       c.classes_graduate_year,  " +
                    "       c.classes_major,  " +
                    "       c.classes_desc  " +
//                    "       c.create_time c_create_time " +
                    "from student S  " +
                    "         join classes c on S.classes_id = c.id";
            ps = c.prepareStatement(sql);
            //4.执行sql语句
            r = ps.executeQuery();
            //4.查询结果集
            while (r.next()) {
                Student student = new Student();
                student.setId(r.getInt("id"));
                student.setStudentName(r.getString("student_name"));
                student.setStudentNo(r.getString("student_no"));
                student.setIdCard(r.getString("id_card"));
                student.setStudentEmail(r.getString("student_email"));
                student.setClassesId(r.getInt("classes_id"));
                student.setCreateTime(new Date(r.getTimestamp("create_time").getTime()));
                Classes classes = new Classes();
                classes.setId(r.getInt("cid"));
                classes.setClassesName(r.getString("classes_name"));
                classes.setClassesGraduateYear(r.getString("classes_graduate_year"));
                classes.setClassesMajor(r.getString("classes_major"));
                classes.setClassesDesc(r.getString("classes_desc"));
                student.setClasses(classes);
                //设置属性，通过结果集获取，并设置
                list.add(student);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException("查询数据字典标签出错", e);
        } finally {
            DBUtil.close(c, ps, r);
        }
    }

    public static Student queryByid(int id) {
        //jdbc查询
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet r = null;
        Student student = new Student();
        try {
            //1.获取数据库连接
            c = DBUtil.getConnection();
            //2.创建操作命令对象
            String sql = "select s.id,  " +
                    "       S.student_name,  " +
                    "       S.student_no,  " +
                    "       S.id_card,  " +
                    "       S.student_email,  " +
                    "       S.classes_id,  " +
                    "       S.create_time,  " +
                    "       c.id cid,  " +
                    "       c.classes_name,  " +
                    "       c.classes_graduate_year,  " +
                    "       c.classes_major,  " +
                    "       c.classes_desc  " +
//                    "       c.create_time c_create_time " +
                    "from student S  " +
                    "         join classes c on S.classes_id = c.id where s.id = ?";
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            //4.执行sql语句
            r = ps.executeQuery();
            //4.查询结果集
            while (r.next()) {
                student.setId(r.getInt("id"));
                student.setStudentName(r.getString("student_name"));
                student.setStudentNo(r.getString("student_no"));
                student.setIdCard(r.getString("id_card"));
                student.setStudentEmail(r.getString("student_email"));
                student.setClassesId(r.getInt("classes_id"));
                student.setCreateTime(new Date(r.getTimestamp("create_time").getTime()));
                Classes classes = new Classes();
                classes.setId(r.getInt("cid"));
                classes.setClassesName(r.getString("classes_name"));
                classes.setClassesGraduateYear(r.getString("classes_graduate_year"));
                classes.setClassesMajor(r.getString("classes_major"));
                classes.setClassesDesc(r.getString("classes_desc"));
                student.setClasses(classes);
                //设置属性，通过结果集获取，并设置
            }
            return student;
        } catch (Exception e) {
            throw new RuntimeException("查询学生详情出错", e);
        } finally {
            DBUtil.close(c, ps, r);
        }
    }

    //插入
    public static void insert(Student s) {
        //jdbc查询
        Connection c = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库连接
            c = DBUtil.getConnection();
            //2.创建操作命令对象
            String sql = "insert into student(student_name,student_no,id_card,student_email,classes_id) values (?,?,?,?,?)";
            ps = c.prepareStatement(sql);
            ps.setString(1, s.getStudentName());
            ps.setString(2, s.getStudentNo());
            ps.setString(3, s.getIdCard());
            ps.setString(4, s.getStudentEmail());
            ps.setInt(5, s.getClassesId());
            //4.执行sql语句
            int num = ps.executeUpdate();
            //4.查询结果集

        } catch (Exception e) {
            throw new RuntimeException("插入学生详情出错", e);
        } finally {
            DBUtil.close(c, ps);
        }
    }

    //修改   （*）
    public static void update(Student s) {
        //jdbc查询
        Connection c = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库连接
            c = DBUtil.getConnection();
            //2.创建操作命令对象
            String sql = "update student set student_name=?,student_no=?,id_card=?,student_email=?,classes_id=? where id=?";
            ps = c.prepareStatement(sql);
            ps.setString(1, s.getStudentName());
            ps.setString(2, s.getStudentNo());
            ps.setString(3, s.getIdCard());
            ps.setString(4, s.getStudentEmail());
            ps.setInt(5, s.getClassesId());
            ps.setInt(6, s.getId());
            //4.执行sql语句
            int num = ps.executeUpdate();
            //4.查询结果集

        } catch (Exception e) {
            throw new RuntimeException("修改学生详情出错", e);
        } finally {
            DBUtil.close(c, ps);
        }
    }

    //删除
    public static void delete(String[] ids) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库连接
            c = DBUtil.getConnection();
            //2.创建操作命令对象
            StringBuilder sql = new StringBuilder("delete from student where id in(");
            for (int i = 0; i < ids.length; i++) {
                if (i != 0)
                    sql.append(",");
                sql.append("?");
            }
            sql.append(")");
            ps = c.prepareStatement(sql.toString());
            for (int i = 0; i < ids.length; i++) {
                ps.setInt(i + 1, Integer.parseInt(ids[i]));
            }
            //4.执行sql语句
            int num = ps.executeUpdate();
            //4.查询结果集

        } catch (Exception e) {
            throw new RuntimeException("删除学生信息出错", e);
        } finally {
            DBUtil.close(c, ps);
        }
    }
}
