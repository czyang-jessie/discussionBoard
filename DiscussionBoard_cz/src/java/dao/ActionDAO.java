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
import model.Action;

/**
 *
 * @author 123
 */
public class ActionDAO {
     public static int retrieveLikesForTopic(int topic_id) {
        int result =0;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(topic_id) FROM action WHERE topic_id=" +topic_id+" and type='L'");

            while (rs.next()) {
                result += rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return result;
    }
     
      public static HashMap<Integer,Integer> retrieveLikesForAllTopics() {
        HashMap<Integer,Integer> result =new HashMap<Integer,Integer>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select topic_id, count(topic_id)as count from action where type='L' group by topic_id");

            while (rs.next()) {
                result.put(rs.getInt("topic_id"),rs.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return result;
    }
      
      public static int retrieveDislikesForTopic(int topic_id) {
        int result =0;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(topic_id) FROM action WHERE topic_id=" +topic_id+" and type='D'");

            while (rs.next()) {
                result += rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return result;
    }
     
      public static HashMap<Integer,Integer> retrieveDislikesForAllTopics() {
        HashMap<Integer,Integer> result =new HashMap<Integer,Integer>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select topic_id, count(topic_id)as count from action where type='D' group by topic_id");

            while (rs.next()) {
                result.put(rs.getInt("topic_id"),rs.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return result;
    }
       public static HashMap<Integer,Integer> retrieveLikesForAllReplies() {
        HashMap<Integer,Integer> result =new HashMap<Integer,Integer>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select reply_id, count(reply_id)as count from action where type='L' and reply_id!= -1  group by reply_id");

            while (rs.next()) {
                result.put(rs.getInt("reply_id"),rs.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return result;
    }
      
      
     
     
      public static HashMap<Integer,Integer> retrieveDislikesForAllReplies() {
        HashMap<Integer,Integer> result =new HashMap<Integer,Integer>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select reply_id, count(reply_id)as count from action where type='D' and reply_id!= -1  group by reply_id");

            while (rs.next()) {
                result.put(rs.getInt("reply_id"),rs.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return result;
    }
      
      
      public static int insert(Action a){
        int i=0;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            i=stmt.executeUpdate("INSERT INTO Action"
				+ " (topic_id, reply_id, user_id, type ) VALUES "
				+ " ("+a.getTopic_id() +"," +a.getReply_id() +", '"+ a.getUser_id()+ "','"+a.getType()+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return i;
    }
}
