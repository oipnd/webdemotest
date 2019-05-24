package com.sei.web.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @ClassName Dao
 * @Description 工具类
 * @Author yyy
 * @Date 2019/4/15 20:55
 * @Version 1.0
 **/
public class DaoUtils {

    private static String url="jdbc:mysql://localhost:3306/readjava_study";
    private static String userName="root";
    private static String passWord="root";
    private static Connection connection=null;



    /*
     * @Author yyy
     * @Description //数据库连接
     * @Date 20:59 2019/4/15
     * @Param []
     * @return java.sql.Connection
     **/
    private static Connection getConnection(){
        if(null==connection){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection=DriverManager.getConnection(url,userName,passWord);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }

}
