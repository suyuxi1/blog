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
//
//import org.apache.commons.beanutils.BeanUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.InvocationTargetException;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//
///**
// * 数据库连接工具类
///**
// * 数据库连接工具类
// *
// * @author mqxu
// * @date 2019-11-07
// */
//public class DbUtil {
//    private static Logger logger = LoggerFactory.getLogger(DbUtil.class);
//
//    private static Properties properties;
//
//    private DbUtil() {
//    }
//
////     使用静态代码块保证在类加载的时候立即加载对应的配置文件
//    static {
//        properties = new Properties();
//        try {
//            InputStream ins = DbUtil.class.getClassLoader().getResourceAsStream("db-config.properties");
//            assert ins != null;
//            properties.load(ins);
//            Class.forName(properties.getProperty("jdbc.driverClassName"));
//        } catch (FileNotFoundException e) {
//            logger.error("数据库配置文件未找到");
//        } catch (IOException e) {
//            logger.error("数据库配置文件读写错误");
//        } catch (ClassNotFoundException e) {
//            logger.error("数据库驱动 未找到");
//        }
//    }
//
//
//    /**
//     * 获得数据库连接Connection
//     *
//     * @return Connection 数据库连接
//     */
//    public static Connection getConnection() {
//
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(
//                    properties.getProperty("jdbc.url"),
//                    properties.getProperty("jdbc.username"),
//                    properties.getProperty("jdbc.password"));
//        } catch (SQLException e) {
//            logger.error("数据库连接失败");
//        }
//        return connection;
//    }
//
//    /**
//     * 关闭connection
//     *
//     * @param connection 连接池对象
//     */
//    public static void close(Connection connection) {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 关闭Statement
//     *
//     * @param statement
//     */
//    public static void close(Statement statement) {
//        if (statement != null) {
//            try {
//                statement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 关闭ResultSet
//     *
//     * @param resultSet
//     */
//    public static void close(ResultSet resultSet) {
//        if (resultSet != null) {
//            try {
//                resultSet.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 关闭Connection 以及Statement
//     *
//     * @param connection
//     * @param statement
//     */
//    public static void close(Connection connection, Statement statement) {
//        close(connection);
//        close(statement);
//    }
//
//    /**
//     * 关闭Connection，Statement以及ResultSet
//     *
//     * @param connection
//     * @param statement
//     * @param resultSet
//     */
//    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
//        close(connection, statement);
//        close(resultSet);
//    }
//
//    /**
//     * 更新操作
//     *
//     * @param sql   执行的SQL语句
//     * @param param 对应的参数列表
//     * @return true 更新成功， false 更新失败
//     */
//    public static boolean update(String sql, Object[] param) {
//
//        PreparedStatement preparedStatement = null;
//        Connection connection = getConnection();
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//
//            if (settingParams(preparedStatement, param) == false) {
//                return false;
//            }
//
//            int result = preparedStatement.executeUpdate();
//            if (result > 0) {
//                return true;
//            }
//            return false;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            close(connection, preparedStatement);
//        }
//        return false;
//    }
//
//    /**
//     * 设置参数
//     *
//     * @param preparedStatement Statement对象
//     * @param param             参数列表
//     * @return
//     * @throws SQLException
//     */
//    private static boolean settingParams(PreparedStatement preparedStatement, Object[] param) throws SQLException {
//
//        if (param != null && param.length > 0) {
//            // 获取ParameterMetaData
//            ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
//            // 获得SQL中占位符个数
//            int paramCount = parameterMetaData.getParameterCount();
//
//            // 占位符个数与参数个数不一致，返回false表示出错
//            if (paramCount != param.length) {
//                return false;
//            }
//            // 设置对应的参数信息
//            for (int i = 0; i < paramCount; i++) {
//                preparedStatement.setObject(i + 1, param[i]);
//            }
//        }
//        return true;
//    }
//
//    /**
//     * 获取单个Bean
//     *
//     * @param sql   执行SQL语句
//     * @param param 对应的参数列表
//     * @param clazz 所要获取的对象的类型
//     * @param <T>   对象的类型
//     * @return
//     */
//    public static <T> T queryForBean(String sql, Object[] param, Class<T> clazz) {
//
//        Connection connection = getConnection();
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//
//            preparedStatement = connection.prepareStatement(sql);
//
//            if (settingParams(preparedStatement, param) == false) {
//                return null;
//            }
//
//            resultSet = preparedStatement.executeQuery();
//            if (resultSet == null) {
//                return null;
//            }
//
//            if (resultSet.next()) {
//                // 利用反射机制创建对象
//                T data = clazz.newInstance();
//                // 获得ResultSetMetaData
//                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//                // 获得列的数量
//                int columnCount = resultSetMetaData.getColumnCount();
//                for (int i = 0; i < columnCount; i++) {
//                    // 获得对应的列的名称
//                    String name = resultSetMetaData.getColumnName(i + 1);
//                    // 获得对应的列的值
//                    Object rData = resultSet.getObject(name);
//                    // 使用BeanUtils工具对属性进行注入
//                    BeanUtils.copyProperty(data, name, rData);
//                }
//                return data;
//
//            } else {
//                return null;
//            }
//
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } finally {
//            close(connection, preparedStatement, resultSet);
//        }
//        return null;
//    }
//
//    /**
//     * 获取Bean并且封装成List
//     *
//     * @param sql   执行SQL语句
//     * @param param 对应的参数列表
//     * @param clazz 所要获取的对象的类型
//     * @param <T>   对象的类型
//     * @return list
//     */
//    public static <T> List<T> queryForList(String sql, Object[] param, Class<T> clazz) {
//
//        Connection connection = getConnection();
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            if (settingParams(preparedStatement, param) == false) {
//                return null;
//            }
//            resultSet = preparedStatement.executeQuery();
//            if (resultSet == null) {
//                return null;
//            }
//            List<T> results = new ArrayList<>();
//            while (resultSet.next()) {
//                // 创建对象
//                T data = clazz.newInstance();
//                // 获得ResultSetMetaData
//                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//                // 获得列的数量
//                int columnCount = resultSetMetaData.getColumnCount();
//                for (int i = 0; i < columnCount; i++) {
//                    // 获得对应的列的名称
//                    String name = resultSetMetaData.getColumnName(i + 1);
//                    // 获得对应的列的值
//                    Object rData = resultSet.getObject(name);
//                    // 使用BeanUtils工具对属性进行注入
//                    BeanUtils.copyProperty(data, name, rData);
//                }
//                results.add(data);
//            }
//            return results;
//
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } finally {
//            close(connection, preparedStatement, resultSet);
//        }
//        return null;
//    }
//
//}
