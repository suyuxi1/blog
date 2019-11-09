package com.scs.web.blog.util;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * 数据库连接工具类
 *
 * @author mqxu
 * @date 2019-11-07
 */
public class DbUtil {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(DbUtil.class);


    /**
     * 读取resources目录下的db-config.properties文件
     */
    private static ResourceBundle rb = ResourceBundle.getBundle("db-config");

    /**
     * 私有的构造方法，单例模式，禁止外部创建对象
     */
    private DbUtil() {
    }

    //使用静态块加载驱动程序，只执行一次
    static {
        URL = rb.getString("jdbc.url");
        USERNAME = rb.getString("jdbc.username");
        PASSWORD = rb.getString("jdbc.password");
        String driver = rb.getString("jdbc.driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义一个获取数据库连接的方法
     *
     * @return Connection
     */
    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                logger.error(LocalDateTime.now() + "数据库连接失败");
            }
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param rs
     * @param stat
     * @param conn
     */
    public static void close(ResultSet rs, Statement stat, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection connection = null;
        for (int i = 0; i < 3; i++) {
            connection = DbUtil.getConnection();
        }
    }
}