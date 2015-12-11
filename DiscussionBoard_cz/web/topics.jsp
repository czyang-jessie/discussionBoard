<%-- 
    Document   : dashboard
    Created on : Dec 10, 2015, 2:13:18 PM
    Author     : 123
--%>
<%@page import="java.util.HashMap"%>
<%@page import="model.Topic"%>
<%@page import="model.Project"%>
<%@page import="java.util.ArrayList"%>
    <head>
         <%@include file="protect.jsp" %>
        <meta charset="UTF-8"> 
    <link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
       <%@include file="head.jsp"%>  
        
       <script type="text/javascript">
           $(document).ready(function() {
            $('#topicTable').DataTable( {
                "order": [[ 6, "desc" ]]
                } );
            } );
        </script>
    </head>
    
    <body>
        <%@include file="nav.jsp"%>
        <div class="container">
    <!-- Page Content -->
    <div class="col-lg-12">
        <h1 class="page-header">
           Topics
           <button class="btn btn-primary " data-toggle="modal" data-target="#newTopicModal"><i class="fa fa-plus"></i>&nbsp;New Post</button>
        </h1>
        <table id="topicTable" name="topicTable" class="table forum table-striped">
                <thead>
                <tr>
                    <th>Topics</th>
                    <th><i class="fa fa-user"></i>&nbsp;Author</th>
                    <th><i class="fa fa-comment"></i> &nbsp;Replies</th>
                    <th><i class="fa fa-thumbs-up"></i> &nbsp;Likes</th>
                    <th><i class="fa fa-thumbs-down"></i>&nbsp;Dislikes</th>
                    <th><i class="fa fa-clock-o"></i> Posted_at</th>
                    <th><i class="fa fa-users"></i> Popularity</th>
                </tr>
                </thead>
                <tbody>
<% 
    String message = (String)request.getAttribute("message");
    ArrayList<String> errorList = (ArrayList<String>) request.getAttribute("errorList");
    HashMap<Integer,String> users = (HashMap<Integer,String>) request.getAttribute("users");
    HashMap<Integer,Integer> likes = (HashMap<Integer,Integer>) request.getAttribute("likes");
    HashMap<Integer,Integer> dislikes = (HashMap<Integer,Integer>) request.getAttribute("dislikes");
    HashMap<Integer,Integer> replies = (HashMap<Integer,Integer>) request.getAttribute("replies");
                ArrayList<Topic>topics = (ArrayList<Topic>) request.getAttribute("topics"); 
                if(!topics.isEmpty()){
                for(Topic t :topics){
        

%>
                <tr>
                    <td>
                        <a href="topic?tid=<%=t.getTopic_id()%>"><%=t.getTitle()%></a>
                    </td>
                    <td><%=users.get(t.getAuthor_id())%><br></td>
                     <td><%if(replies.get(t.getTopic_id())==null){
                        out.print("0");
                    }else{
                        out.print(replies.get(t.getTopic_id()));
                    }%></td>
                    <td><%if(likes.get(t.getTopic_id())==null){
                        out.print("0");
                    }else{
                        out.print(likes.get(t.getTopic_id()));
                    }%></td>
                    <td><%if(dislikes.get(t.getTopic_id())==null){
                        out.print("0");
                    }else{
                        out.print(dislikes.get(t.getTopic_id()));
                    }%></td>
                    <td><%=(t.getPosted_at()).substring(0,19)%></td>
                    <td> <%=t.getPopularity()%></td>
                  
                </tr>
        <%}}%>
                </tbody>
        </table>
    </div>
        </div>
                
    <div class="modal fade" id="newTopicModal" tabindex="-1" role="dialog" >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" >New Post</h4>
                </div>
                <div class="modal-body">
                    <form role="form" action="new_topic" method="post">
                        <div class="form-group">
                            <label for="title">Title:</label>
                            <input type="text" class="form-control"  id="title" name="title" >
                        </div>
                        <div class="form-group">
                            <label for="description">Content:</label>
                            <textarea class="form-control" rows="4" id="content" name="content"></textarea>
                        </div>
                        <input type="hidden" class="form-control" value="<%=(Integer)request.getAttribute("pid")%>"  id="project_id" name="project_id" >
                        <input type="hidden" class="form-control" value="<%=loggedInUser.getUser_id()%>"  id="author_id" name="author_id" >
                    
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <input type="submit" class="btn btn-primary" id="submit" name="submit" value="submit">
                </div>
                </form>
            </div>
        </div>
    </div>
    </body>
</html>
