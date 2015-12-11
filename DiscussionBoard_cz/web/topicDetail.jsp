<%-- 
    Document   : topicDetail
    Created on : Dec 10, 2015, 5:02:57 PM
    Author     : 123
--%>

<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="model.Reply"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.Topic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8"> 

        <%@include file="head.jsp"%>  

    </head>
    <body>
        <%@include file="protect.jsp" %>
        <%@include file="nav.jsp"%>
        <%
            int t_likes = (Integer) request.getAttribute("topic_likes");
            int t_dislikes = (Integer) request.getAttribute("topic_dislikes");
            Topic t = (Topic) request.getAttribute("topics");
            HashMap<Integer, String> users = (HashMap<Integer, String>) request.getAttribute("users");
            HashMap<Integer, Integer> reply_likes = (HashMap<Integer, Integer>) request.getAttribute("reply_likes");
            HashMap<Integer, Integer> reply_dislikes = (HashMap<Integer, Integer>) request.getAttribute("reply_dislikes");
            HashMap<Integer,List<Reply>> subReplies = (HashMap<Integer, List<Reply>>) request.getAttribute("subReplies");
        %>
        <div class="container">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <ol class="breadcrumb">
                        <li><a href="topics?pid=<%=t.getProject_id()%>">Topic List</a></li>
                        <li class="active">View Topic</li>
                    </ol>
                    <div class="panel-body">
                        <h3><%=t.getTitle()%></h3>

                        <!-- Author -->

                        <p class="pull-right">
                            <i class="fa fa-user"></i>&nbsp;<%=users.get(t.getAuthor_id())%>
                            &nbsp;<i class="fa fa-clock-o"></i>&nbsp;<%=(t.getPosted_at()).substring(0, 19)%>&nbsp;
                            &nbsp;<i class="fa fa-users"></i><%=t.getPopularity()%>&nbsp;
                            <a href="new_action?uid=<%=loggedInUser.getUser_id()%>&type=L&tid=<%=t.getTopic_id()%>"><i class="fa fa-thumbs-up"></i><%=t_likes%></a>&nbsp;
                            <a href="new_action?uid=<%=loggedInUser.getUser_id()%>&type=D&tid=<%=t.getTopic_id()%>"><i class="fa fa-thumbs-down"></i><%=t_dislikes%></a>
                        </p>
                        <br>
                        <hr>
                        <!-- Post Content -->
                        <%=t.getContent()%>

                        <hr>

                        <div class="well">
                            <h4>Reply to post:</h4>
                            <form method="post" action="new_reply" role="form">
                                <div class="form-group">
                                    <textarea class="form-control" rows="3" id="content" name="content" required></textarea>
                                </div>
                                <div class="form-group">
                                    <input type="hidden" class="form-control" id="topic_id" name="topic_id" value="<%=t.getTopic_id()%>">
                                    <input type="hidden" class="form-control" id="author_id" name="author_id" value="<%=loggedInUser.getUser_id()%>">
                                </div>
                                <input type="submit"  value="submit"  class="btn btn-primary">
                            </form>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <strong>Replies</strong>
                        </h3>
                    </div>

                    <div class="panel-body">
                        <!-- Author -->
                        <table class="table table-striped table-bordered">
                            <% ArrayList<Reply> replies = (ArrayList<Reply>) request.getAttribute("replies");
                                for (Reply r : replies) {
                            %>
                            <!---Reply block-->
                            <tr>
                                <td>
                                    <p ><%=r.getContent()%></p>
                                    <button class="btn btn-default btn-sm pull-right" data-toggle="modal" data-target="#newReplyModal<%=r.getReply_id()%>">Reply</button>
                                    <p >
                                        <i class="fa fa-user"></i>&nbsp;<%=users.get(r.getAuthor_id())%>
                                        &nbsp;<i class="fa fa-clock-o"></i>&nbsp;<%=(r.getPosted_at()).substring(0, 19)%>&nbsp;
                                        &nbsp;<i class="fa fa-users"></i><%=r.getPopularity()%>&nbsp;
                                        <a href="new_action?uid=<%=loggedInUser.getUser_id()%>&type=L&rid=<%=r.getReply_id()%>&tid=<%=t.getTopic_id()%>"><i class="fa fa-thumbs-up"></i> <%if (reply_likes.get(r.getReply_id()) == null) { out.print("0");
                                        } else {
                                            out.print(reply_likes.get(r.getReply_id()));
                                        }%></a>&nbsp;
                                        <a href="new_action?uid=<%=loggedInUser.getUser_id()%>&type=D&rid=<%=r.getReply_id()%>&tid=<%=t.getTopic_id()%>"><i class="fa fa-thumbs-down"></i> <%if (reply_dislikes.get(r.getReply_id()) == null) {
                                        out.print("0");
                                        } else {
                                            out.print(reply_dislikes.get(r.getReply_id()));
                                        }%></a>
                                    </p>
                               
                            
                            <!--- sub-reply list-->
                            <% if(subReplies.get(r.getReply_id())!=null){
                                out.print("<hr>");
                                List<Reply> subReps = subReplies.get(r.getReply_id());
                                Collections.sort(subReps);
                                for(Reply reply:subReps){%>
                                <p class='col-md-offset-1'><%=reply.getContent()%> <br>
                                 <i class="fa fa-user"></i>&nbsp;<%=users.get(reply.getAuthor_id())%>
                                    &nbsp;<i class="fa fa-clock-o"></i>&nbsp;<%=(reply.getPosted_at()).substring(0, 19)%>&nbsp;
                                    &nbsp;<i class="fa fa-users"></i><%=reply.getPopularity()%>&nbsp;
                                    <a href="new_action?uid=<%=loggedInUser.getUser_id()%>&type=L&rid=<%=reply.getReply_id()%>&tid=<%=t.getTopic_id()%>"><i class="fa fa-thumbs-up"></i><%if (reply_likes.get(reply.getReply_id()) == null) { out.print("0");
                                    } else {
                                        out.print(reply_likes.get(reply.getReply_id()));
                                    }%></a>&nbsp;
                                    <a href="new_action?uid=<%=loggedInUser.getUser_id()%>&type=D&rid=<%=reply.getReply_id()%>&tid=<%=t.getTopic_id()%>"><i class="fa fa-thumbs-down"></i><%if (reply_dislikes.get(reply.getReply_id()) == null) {
                                    out.print("0");
                                    } else {
                                        out.print(reply_dislikes.get(reply.getReply_id()));
                                    }%></a>
                                </p>
                                <hr>
                            <%}}%>
                             </td>
                            </tr>
                            <!---Reply to reply modal-->
                            <div class="modal fade" id="newReplyModal<%=r.getReply_id()%>" tabindex="-1" role="dialog" >
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                            <h4 class="modal-title" >New Reply</h4>
                                        </div>
                                        <div class="modal-body">
                                            <form role="form" action="new_reply" method="post">
                                                <div class="form-group">
                                                    <label for="description">Content:</label>
                                                    <textarea class="form-control" rows="4" id="content" name="content"></textarea>
                                                </div>
                                                <input type="hidden" class="form-control" value="<%=t.getTopic_id()%>"  id="topic_id" name="topic_id" >
                                                <input type="hidden" class="form-control" value="<%=loggedInUser.getUser_id()%>"  id="author_id" name="author_id" >
                                                <input type="hidden" class="form-control" value="<%=r.getReply_id()%>"  id="reply_to_reply_id" name="reply_to_reply_id" >

                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                                    <input type="submit" class="btn btn-primary" id="submit" name="submit" value="submit">
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <%}%>
                        </table>

                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
