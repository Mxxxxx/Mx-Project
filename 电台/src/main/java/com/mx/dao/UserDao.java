package com.mx.dao;

import com.mx.model.User;
import com.mx.util.DB;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Java 代码表示的 INSERT/UPDATE/DELETE/SELECT
 * <p>
 * 关于用户的密码，不要保存明文密码（peixinchen/123）
 * 如果数据从你这里泄露了，则所有用户的密码也跟着全部泄露了
 * 一般都是保存做完 hash 的密码（这里选择 sha-256 这个hash算法）
 */
public class UserDao {
    public User insert(String username, String plainPassword) {
        String password = encrypt(plainPassword);
        String sql = "insert into users (username, password) values (?, ?)";

        try (Connection connection = DB.getConnection()) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User select(String username, String password) {
        return null;
    }

    // 这个做法实际上也不适合生产环境真正使用
    // 但至少比明文的情况要安全一点
    private String encrypt(String plain) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = plain.getBytes();
            byte[] digest = messageDigest.digest(bytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String a = "123";
        UserDao userDao = new UserDao();
        String encrypt = userDao.encrypt(a);
        System.out.println(encrypt);
    }
}
