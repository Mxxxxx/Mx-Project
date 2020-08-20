package com.mx.model;

import java.util.Objects;

/**
 * @Author Meng Xin
 * @Date 2020/8/20 18:52
 */
public class User {
    public int uid;
    public String username;

    public User() {

    }

    public User(int uid, String username) {
        this.uid = uid;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return uid == user.uid &&
                Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, username);
    }
}
