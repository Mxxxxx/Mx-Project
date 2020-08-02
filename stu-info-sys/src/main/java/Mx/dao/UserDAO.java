package Mx.dao;

import Mx.model.Classes;
import Mx.model.Student;
import Mx.model.User;
import Mx.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 * @Author Meng Xin
 * @Date 2020/8/2 18:10
 */
public class UserDAO {
    //（*）
    public static User query(User u) {
        //jdbc查询
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet r = null;
        User user = null;
        try {
            //1.获取数据库连接
            c = DBUtil.getConnection();
            //2.创建操作命令对象
            String sql = "select id, nickname, email, create_time from user where username = ? and password = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            //4.执行sql语句
            r = ps.executeQuery();
            //4.查询结果集
            while (r.next()) {
                user = u;
                user.setId(r.getInt("id"));
                user.setNickname(r.getString("nickname"));
                user.setEmail(r.getString("email"));
                user.setCreateTime(new Date(r.getTimestamp("create_time").getTime()));
                //设置属性，通过结果集获取，并设置
            }
            return user;
        } catch (Exception e) {
            throw new RuntimeException("用户名密码错误", e);
        } finally {
            DBUtil.close(c, ps, r);
        }
    }
}
