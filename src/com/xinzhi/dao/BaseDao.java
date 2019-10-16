package com.xinzhi.dao;

import java.sql.*;

public class BaseDao {
    private static String driver = "com.mysql.jdbc.Diver";
    private static String url = "jdbc:mysql://localhost:3306/hehe";
    private static String user = "root";
    private static String password = "root";
    protected Connection conn = null;
    protected PreparedStatement pstmt = null;
    protected Connection getConn () throws ClassNotFoundException,SQLException {
        Class.forName(driver);
        conn = DriverManager.getConnection(url,user,password);
        return conn;
    }

    protected ResultSet executeQuerySQL(String sql, Object[] params) throws SQLException {
        ResultSet rs = null;
        try{
            conn = getConn();
        }catch ( ClassNotFoundException e){
            e.printStackTrace();
        }
        pstmt = conn.prepareStatement(sql);
        if(params != null){
            for( int i = 0 ; i < params.length ; i++){
                pstmt.setObject(i+1, params[i]);
            }
        }
        rs = pstmt.executeQuery();
        return rs;
    }

    protected int executeUpdate(String sql,Object[] params) throws SQLException{
        try{
            conn = getConn();
        }catch ( ClassNotFoundException e){
            e.printStackTrace();
        }
        int num = 0;
        pstmt = conn.prepareStatement(sql);
        if(params != null){
            for( int i = 0 ; i < params.length ; i++){
                pstmt.setObject(i+1, params[i]);
            }
        }
        num = pstmt.executeUpdate();
        return num;
    }
    protected void closeAll(Connection conn , PreparedStatement pstmt , ResultSet rs){
        try {
            if(rs != null)
                rs.close();
            if(pstmt != null)
                pstmt.close();
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}