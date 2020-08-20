package com.mx.dao;

import com.mx.model.Book;
import com.mx.model.User;
import com.mx.util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public Book insert(User user, String title) {
        return null;
    }

    public List<Book> selectAll() throws SQLException {
        // 联表查询
        String sql = "select bid, title, users.uid, users.username " +
                "from books, users " +
                "where books.uid = users.uid " +
                "order by bid desc";

        List<Book> books = new ArrayList<>();
        try (Connection c = DB.getConnection()) {
            try (PreparedStatement s = c.prepareStatement(sql)) {
                try (ResultSet r = s.executeQuery()) {
                    while (r.next()) {
                        User user = new User(r.getInt("uid"), r.getString("username"));
                        Book book = new Book(r.getInt("bid"), user, r.getString("title"));

                        books.add(book);
                    }
                }
            }
        }
        return books;
    }
}
