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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import model.Reply;
import model.Topic;

/**
 *
 * @author 123
 */
public class ReplyDAO {
     public static ArrayList<Reply> retrieveByTopicID(int topic_id) {
        Reply r = null;
        ArrayList<Reply> list = new ArrayList<Reply>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from reply where topic_id = " +topic_id + " and reply_to_reply_id = -1");

            while (rs.next()) {
                r = new Reply(rs.getInt("reply_id"),rs.getInt("topic_id"),rs.getInt("reply_to_reply_id"), rs.getInt("author_id"),rs.getString("content"),
                        rs.getString("posted_at"));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        for(Reply reply: list){
            addPopularity(reply);
        }
        Collections.sort(list);
        return list;
    }
    
     //popularity is calculated based on (#likes - #dislikes)
    private static Reply addPopularity(Reply r) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        if(r!=null){
            try {
                conn = ConnectionManager.getConnection();
                stmt = conn.createStatement();
              
                rs = stmt.executeQuery("select( (select count(*) from action where reply_id = " +r.getReply_id()+" and type='L') -\n" +
"(select count(*) from action where reply_id =" +r.getReply_id()+ " and type='D') ) as popularity");

                while (rs.next()) {
                   r.setPopularity(rs.getInt("popularity"));
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectionManager.close(conn, stmt, rs);
            }
        }
        
        return r ;
    }
    
    public static HashMap<Integer,Integer> noOfRepliesByTopic(){
          HashMap<Integer,Integer> result = new  HashMap<Integer,Integer>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select topic_id, count(topic_id)as count from reply group by topic_id");

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
    
    public static int insert(Reply r){
        int i=0;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            i=stmt.executeUpdate("INSERT INTO Reply"
				+ " (topic_id, author_id, reply_to_reply_id, content ) VALUES "
				+ " ("+r.getTopic_id() +"," +r.getAuthor_id() +", '"+ r.getReply_to_reply_id()+ "','"+r.getContent()+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return i;
    }
    
    
     public static HashMap<Integer,List<Reply>> getSubReplies(int topic_id){
          HashMap<Integer,List<Reply>> result = new  HashMap<Integer,List<Reply>>();
         List<Reply> replies = new ArrayList<Reply>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from reply where reply_to_reply_id !=-1 and topic_id = "+topic_id);

            while (rs.next()) {
                replies.add(new Reply(rs.getInt("reply_id"),rs.getInt("topic_id"),rs.getInt("reply_to_reply_id"), rs.getInt("author_id"),rs.getString("content"),
                        rs.getString("posted_at")));
            }
            for(Reply r:replies){
                addPopularity(r);
                if(result.containsKey(r.getReply_to_reply_id())){
                    result.get(r.getReply_to_reply_id()).add(r);
                }else{
                    List<Reply> subList = new ArrayList<Reply>();
                    subList.add(r);
                    result.put(r.getReply_to_reply_id(), subList);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return result;
    }
    
   
}
