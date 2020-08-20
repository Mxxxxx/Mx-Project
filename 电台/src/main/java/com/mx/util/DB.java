package com.mx.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author Meng Xin
 * @Date 2020/8/20 19:00
 */
public class DB {
    private static volatile DataSource dataSource = null;

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            synchronized (DB.class) {
                if (dataSource == null) {
                    dataSource = initDadaSource();
                }
            }
        }
        return dataSource.getConnection();
    }

    private static DataSource initDadaSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUser("root");
        mysqlDataSource.setDatabaseName("hjb2_tingshu");
        mysqlDataSource.setPassword("199846");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setUseSSL(false);
        return mysqlDataSource;
    }
}
