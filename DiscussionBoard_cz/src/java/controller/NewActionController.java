/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ActionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Action;

/**
 *
 * @author 123
 */
@WebServlet(name = "NewActionController", urlPatterns = {"/new_action"})
public class NewActionController extends HttpServlet {

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
           int tid= Integer.parseInt(request.getParameter("tid"));
            int uid= Integer.parseInt(request.getParameter("uid"));
           String type= (String)request.getParameter("type");
           int rid = -1;
           boolean actionForTopic=true;
           if(request.getParameter("rid")!=null){
               rid=Integer.parseInt(request.getParameter("rid"));
               actionForTopic = false;
           }
           Action a = new Action();
          if(actionForTopic){
           a.setTopic_id(tid);
           a.setReply_id(-1);
          }else{
           a.setReply_id(rid);
           a.setTopic_id(-1);
          }
          a.setUser_id(uid);
          a.setType(type);
           if(ActionDAO.insert(a)!=1){
               response.sendRedirect("error.jsp");
           }
           
           response.sendRedirect("topic?tid="+tid);
           return;
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
