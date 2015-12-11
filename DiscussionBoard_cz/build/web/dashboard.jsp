<%-- 
    Document   : dashboard
    Created on : Dec 10, 2015, 2:13:18 PM
    Author     : 123
--%>
<%@page import="java.util.HashMap"%>
<%@page import="model.Project"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="protect.jsp" %>
       <%@include file="head.jsp"%> 
    </head>
    

    <body>
        <%@include file="nav.jsp"%>
        <div class="container">
    <!-- Page Content -->
    <div class="col-lg-12">
        <h1 class="page-header">
            Project Board
        </h1>
<% 
    HashMap<Integer,String> dates = (HashMap<Integer,String>)request.getAttribute("dates");
    HashMap<Integer,Integer> topics = (HashMap<Integer,Integer>)request.getAttribute("topics");
    ArrayList<String> errorList = (ArrayList<String>) request.getAttribute("errorList");
            if ( errorList.isEmpty()) {
                ArrayList<Project> projects = (ArrayList<Project>) request.getAttribute("projects"); 
                if(!projects.isEmpty()){
                for(Project p :projects){
        

%>
        <div class=" col-lg-4">
            <div class="panel panel-info">
                <div class="panel-heading" style="text-align:center;height:60px" ><strong><%=p.getProject_title()%></strong></div>
                <div class="panel-body" style="font-size:15px" >
                    <table class="table table-condensed">
                        <tr>
                            <td><i class="fa fa-calendar-check-o"></i>&nbsp;<strong>#Topics </strong></td>
                            <td><%if(topics.get(p.getProject_id())==null){
                                    out.print("0");
                                }else{
                                    out.print(topics.get(p.getProject_id()));
                                }%>
                        </td>
                        </tr>
                        <tr>
                            <td>  <i class="fa fa-clock-o"></i>&nbsp;<strong>Last Post at </strong></td>
                            <td> <%if(dates.get(p.getProject_id())==null){
                                    out.print("-");
                                }else{
                                    out.print(dates.get(p.getProject_id()));
                                }%></td>
                        </tr>
                    </table>
                    <form action="topics" method="post">
                        <input type="hidden" value ="<%=p.getProject_id()%>" id="pid" name="pid">
                        <input type="submit" value="view forum" class="btn pull-right btn-info">
                    </form>
                </div>
            </div>
        </div>
        <%}}}%>
    </div>
        </div>
    </body>
</html>
