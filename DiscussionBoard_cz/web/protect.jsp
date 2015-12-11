

<%@page import="model.User"%>
<%
    User loggedInUser = (User) session.getAttribute("loggedInUser");
   
    
    if (loggedInUser == null) {
        request.setAttribute("message", "Please login to view this page.");
        request.getRequestDispatcher("login.jsp").forward(request, response);
        return;
    } 
%>
