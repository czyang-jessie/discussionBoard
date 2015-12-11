/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ReplyDAO;
import dao.TopicDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "NewReplyController", urlPatterns = {"/new_reply"})
public class NewReplyController extends HttpServlet {

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
          
            int tid= Integer.parseInt(request.getParameter("topic_id"));
            int author_id= Integer.parseInt(request.getParameter("author_id"));
           String content= (String)request.getParameter("content");
           int reply_to_reply_id = -1;
           if(request.getParameter("reply_to_reply_id")!=null){
               reply_to_reply_id=Integer.parseInt(request.getParameter("reply_to_reply_id"));
           }
           Reply r = new Reply();
           r.setContent(content);
           r.setAuthor_id(author_id);
           r.setTopic_id(tid);
           r.setReply_to_reply_id(reply_to_reply_id);
           if(ReplyDAO.insert(r)!=1){
               response.sendRedirect("error.jsp");
               return;
           }
           
           response.sendRedirect("topic?tid="+tid);
           return;
           //RequestDispatcher rd = request.getRequestDispatcher("topic?tid="+tid);
            //rd.forward(request,response);
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
