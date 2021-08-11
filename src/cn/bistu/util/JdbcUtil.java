package cn.bistu.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * jdbc工具类
 */
public class JdbcUtil {
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static String driverCLass = null;
    /**
     * 静态代码块中（只加载一次）
     */
    static {
        //注册驱动程序
        try {
            //读取db.properties文件
            Properties properties = new Properties();
            /**
             * . 代表java命令运行的目录
             * 在java项目下，. java命令的运行目录从项目的根目录开始
             * 在web项目下，. java命令的而运行目录从tomcat/bin目录开始
             * 所以不能使用.
             */
            InputStream in = JdbcUtil.class.getResourceAsStream("/db.properties");
            //加载文件
            properties.load(in);
            //读取信息
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driverCLass = properties.getProperty("driverClass");

            //注册驱动程序
            Class.forName(driverCLass);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("驱动程序注册出错");
        }
    }
    /**
     * 抽取获取连接对象的方法
     */
    public static Connection getConnection(){
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /**
     * 释放资源方法
     */
    public static void close(Connection conn, Statement stmt){
        if (stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new RuntimeException(e1);
            }

        }
        if (stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
