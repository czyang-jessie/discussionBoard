/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import model.User;

/**
 *
 * @author 123
 */
public class UserDAO {

   
    public static User retrieveByUsername(String username) {
        User u = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from user where username = '"+username+"'");

            while (rs.next()) {
                u = new User(rs.getString("username"), rs.getString("password"));
                u.setUser_id(rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return u;
    }

    public static HashMap<Integer, String> getNameIDPair(){
        HashMap<Integer,String> result = new HashMap<Integer,String>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from user");

            while (rs.next()) {
                result.put(rs.getInt("user_id"), rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return result;
    }

}

