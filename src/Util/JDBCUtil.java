package Util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {

    private static Properties pro = new Properties(); // 加载配置信息的对象
    // 初始化配置信息对象
    static { // 设置输入流为要加载的信息

        InputStream read = Thread.currentThread().getContextClassLoader().getResourceAsStream("mysql.properties");
        try {

            pro.load(read); // 将读出的信息加载入pro

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public static Connection getConnection() { // 获得数据库连接

        Connection conn = null;
        try {
            // 加载数据库驱动
            String driverClassName = pro.getProperty("driverClassName");
            Class.forName(driverClassName);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        try {
            // 获取数据库相关信息
            String URL = pro.getProperty("url");
            String USER = pro.getProperty("username");
            String PASSWORD = pro.getProperty("password");

            // 获取数据库连接对象
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return conn;

    }

    public static void close(Connection conn, PreparedStatement preState, ResultSet resSet) { // 关闭数据库连接

        if (resSet != null) {
            try {
                resSet.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }

        if (preState != null) {
            try {
                preState.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static int update(String sql, Object... ls) { // 更新数据封装实现
//        if (ls == null || ls.length == 0) {
//            return 0;
//        }

        Connection conn = JDBCUtil.getConnection();
        PreparedStatement preState = null;
        int numOfColumn = 0;
        try {

            preState = conn.prepareStatement(sql); // 获取预处理对象
            for (int i = 1; i <= ls.length; i++) { // 填入预处理数据

                preState.setObject(i, ls[i - 1]);

            }

            numOfColumn = preState.executeUpdate(); // 执行sql语句

        } catch(SQLException e) {

            e.printStackTrace();

        }

        JDBCUtil.close(conn, preState, null); // 关闭连接

        return numOfColumn;

    }

    public static <T> T query(String sql, ResultSetHandler<T> resHan, Object... ls) { // 查找数据封装实现

        if (ls == null) {
            return null;
        }

        Connection conn = JDBCUtil.getConnection();	// 创建连接
        PreparedStatement preState = null; // 预处理对象
        ResultSet resSet = null;

        try {

            preState = conn.prepareStatement(sql); // 获取预处理对象

            for (int i = 1; i <= ls.length; i++) { // 填充预处理对象
                preState.setObject(i, ls[i - 1]);
            }

            resSet = preState.executeQuery(); // 执行sql语句

        } catch(SQLException e) {

            e.printStackTrace();

        }

        T ans = resHan.getData(resSet); // 获取结果

        JDBCUtil.close(conn, preState, resSet); // 关闭连接

        return ans; // 返回getData实现函数返回的对象

    }

}
