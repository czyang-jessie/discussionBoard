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
import java.util.ArrayList;
import java.util.HashMap;
import model.Project;

/**
 *
 * @author 123
 */
public class ProjectDAO {
     public static ArrayList<Project> retrieveAll() {
        Project p = null;
        ArrayList<Project> list = new ArrayList<Project>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from project ");

            while (rs.next()) {
                p = new Project(rs.getInt("project_id"), rs.getString("project_name"),rs.getString("project_description"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return list;
    }
     
     public static HashMap<Integer,Integer> noOfTopicsByProject(){
          HashMap<Integer,Integer> result = new  HashMap<Integer,Integer>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select project_id, count(topic_id)as count from topic group by project_id");

            while (rs.next()) {
                result.put(rs.getInt("project_id"),rs.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return result;
    }
     
     public static HashMap<Integer,String> lastUpdateByProject(){
          HashMap<Integer,String> result = new  HashMap<Integer,String>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select project_id, max(posted_at)as date from topic group by project_id");

            while (rs.next()) {
                result.put(rs.getInt("project_id"),(rs.getString("date")).substring(0,19));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return result;
    }
     
     
}
