package com.whd.interview.preparation.concurrency.threadlocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author whd.java@gmail.com
 * @date 2019/4/24 17:36
 * @apiNote Describe the function of this class in one sentence
 */
public class ThreadLocalConnectionDemo {

    private static ThreadLocal<Connection> connectionHolder
            = ThreadLocal.withInitial(() -> {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/pzh_qa?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT",
                    "root", "whd12456");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    });

    private static Connection getConnection() {
        return connectionHolder.get();
    }


    public static void main(String[] args) {
        System.out.println(getConnection());
    }

}
