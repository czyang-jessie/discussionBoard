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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Topic;

/**
 *
 * @author 123
 */
@WebServlet(name = "TopicController", urlPatterns = {"/topics"})
public class TopicController extends HttpServlet {

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
            Integer pid =Integer.parseInt(request.getParameter("pid"));
            HashMap<Integer,String> users = UserDAO.getNameIDPair();
            HashMap<Integer,Integer>likes = ActionDAO.retrieveLikesForAllTopics();
            HashMap<Integer,Integer>dislikes = ActionDAO.retrieveDislikesForAllTopics();
            HashMap<Integer,Integer>replies = ReplyDAO.noOfRepliesByTopic();
            ArrayList<Topic> topics = TopicDAO.retrieveByProjectID(pid);
            
            if(topics.isEmpty()){
               errorList.add("There are no topics under this project yet.");
            }
             request.setAttribute("errorList", errorList);
             request.setAttribute("users",users);
             request.setAttribute("topics", topics);
             request.setAttribute("likes", likes);
             request.setAttribute("dislikes", dislikes);
             request.setAttribute("replies", replies);
             request.setAttribute("pid",pid);
            RequestDispatcher rd = request.getRequestDispatcher("topics.jsp");
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
