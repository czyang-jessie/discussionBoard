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
import model.Topic;

/**
 *
 * @author 123
 */
public class TopicDAO {
    public static ArrayList<Topic> retrieveByProjectID(int project_id) {
        Topic t = null;
        ArrayList<Topic> list = new ArrayList<Topic>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from topic where project_id = " +project_id);

            while (rs.next()) {
                t = new Topic(rs.getInt("topic_id"), rs.getInt("author_id"),rs.getInt("project_id"),rs.getString("title"),
                        rs.getString("content"),rs.getString("posted_at"));
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return addPopularity(list);
    }
    
    private static ArrayList<Topic> addPopularity(ArrayList<Topic> list) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        if(!list.isEmpty()){
            try {
                conn = ConnectionManager.getConnection();
                stmt = conn.createStatement();
                for(Topic t: list){
                rs = stmt.executeQuery("select( (select count(*) from action where topic_id = " +t.getTopic_id()+" and type='L') -\n" +
"(select count(*) from action where topic_id =" +t.getTopic_id()+ " and type='D') \n" +
"+(select count(*) from reply where topic_id= " +t.getTopic_id()+ ") ) as popularity");

                while (rs.next()) {
                   t.setPopularity(rs.getInt("popularity"));
                }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectionManager.close(conn, stmt, rs);
            }
        }
        return list;
    }
    
    public static Topic retrieveByTopicID(int topic_id) {
        Topic t = null;
        ArrayList<Topic> list = new ArrayList<Topic>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from topic where topic_id = " +topic_id);

            while (rs.next()) {
                t = new Topic(rs.getInt("topic_id"), rs.getInt("author_id"),rs.getInt("project_id"),rs.getString("title"),
                        rs.getString("content"),rs.getString("posted_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return t;
    }
    
    public static int insert(Topic t){
        int i=0;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            i=stmt.executeUpdate("INSERT INTO Topic"
				+ " (project_id, author_id, title, content ) VALUES "
				+ " ("+t.getProject_id() +"," +t.getAuthor_id() +", '"+ t.getTitle()+ "','"+t.getContent()+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return i;
    }
}
