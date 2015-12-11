/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ActionDAO;
import dao.ReplyDAO;
import dao.TopicDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Reply;
import model.Topic;

/**
 *
 * @author 123
 */
@WebServlet(name = "TopicDetailController", urlPatterns = {"/topic"})
public class TopicDetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            ArrayList<String> errorList = new ArrayList<String>();
            Integer tid =Integer.parseInt(request.getParameter("tid"));
            HashMap<Integer,String> users = UserDAO.getNameIDPair();
            Topic topic = TopicDAO.retrieveByTopicID(tid);
            int topic_likes =ActionDAO.retrieveLikesForTopic(tid);
            int topic_dislikes =ActionDAO.retrieveDislikesForTopic(tid);
            HashMap<Integer,Integer>reply_likes = ActionDAO.retrieveLikesForAllReplies();
            HashMap<Integer,Integer>reply_dislikes = ActionDAO.retrieveDislikesForAllReplies();
            HashMap<Integer,List<Reply>> subReplies = ReplyDAO.getSubReplies(tid);
            ArrayList<Reply> replies = ReplyDAO.retrieveByTopicID(tid);
            if(topic== null){
               errorList.add("Internal error.");
            }
             request.setAttribute("errorList", errorList);
             request.setAttribute("users",users);
             request.setAttribute("topics", topic);
             request.setAttribute("replies", replies);
             request.setAttribute("topic_likes", topic_likes);
             request.setAttribute("topic_dislikes", topic_dislikes);
             request.setAttribute("reply_likes", reply_likes);
             request.setAttribute("reply_dislikes", reply_dislikes);
             request.setAttribute("subReplies", subReplies);
            RequestDispatcher rd = request.getRequestDispatcher("topicDetail.jsp");
            rd.forward(request, response);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
