package Mx.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @Author Meng Xin
 * @Date 2020/7/26 11:03
 */
public class DBUtil {
    /*jdbc步骤
    1.创建数据库连接Connection
    2.创建操作命令对象Statment
    3.执行SQL
    4.根据操作处理
    5.释放资源
    * */
    private static final String URL = "jdbc:mysql://localhost:3306/stu_info?characterEncoding=UTF-8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "199846";

    private static volatile DataSource DS;

    private DBUtil() {
    }

    private static DataSource getDataSource() {
        if (DS == null) {
            synchronized (DBUtil.class) {
                if (DS == null) {
                    DS = new MysqlDataSource();
                    ((MysqlDataSource) DS).setURL(URL);
                    ((MysqlDataSource) DS).setUser(USERNAME);
                    ((MysqlDataSource) DS).setPassword(PASSWORD);
                }
            }
        }
        return DS;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("获取数据库连接失败", e);
        }
    }

    public static void close(Connection c, Statement s) {
        close(c, s, null);
    }

    public static void close(Connection c, Statement s, ResultSet r) {
        try {
            if (r != null) {
                r.close();
            }
            if (s != null) {
                s.close();
            }
            if (r != null) {
                r.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("释放数据库连接失败", e);
        }
    }
}
