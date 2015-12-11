Discussion Board - author:Yang Chengzhen

This java web application is built based on javaEE6, Tomcat 7 with Netbeans 8.0.
The deployed version can be found at http://discussionboard-czyang.rhcloud.com/DiscussionBoard_cz/
Login credential:username:user1 password:123456
(loading time may be slow because of free host server speed limitation)

Assumptions:
1.User accounts, projects are preloaded, system currently does not support add new project and user registration
2.User can reply to a reply, but cannot reply to a 'sub-reply(a reply that is replying to another reply').
3.Popularity of the topic = #of Likes+ #of Replies -#of dislikes
4.Popularity of replies = #of Likes- #of dislikes
5.Pending:proper informational message to user.(Like wrong password, system error and so on)
6.The system time of the deployed version may differ since Database Host is not in Singapore.

Extra function achieved:
1.Sort Topics by title, #of likes/dislikes, popularity
2.Dynamic sorting of topics and replies based on action