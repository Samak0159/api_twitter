<%@ page import="java.util.ArrayList" %>
<%@ page import="metier.User" %>
<%@ page import="twitter4j.Status" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Friends Timeline</title>
    </head> 

        <div class="container">
            <%
                User user = (User) request.getAttribute("user");

                ArrayList<Status> status = user.getCurrentTweets();
                
                for (Status st : status) {
            %>
            
            <!-- BLOCK TWEET -->
            <div class="container" >
                <div class="col-sm-offset-3 col-sm-6">
                    <div class="col-sm-2">
                        <img style="margin-top:5px;" src="<%out.println(st.getUser().getProfileImageURL());%>">
                    </div>
                    <div class="col-sm-10">
                        <strong class="fullname"> <%out.println(st.getUser().getScreenName());%></strong>
                        <span>- @<% out.print(st.getUser().getName());%></span>
                        <p><%out.println(st.getText());%>
                            </br>
                        <a href="favourites.jsp" class="btn btn-primary btn-sm btn pull-right" role="button" aria-pressed="true"><i class="glyphicon glyphicon-star"></i></a>
                        <a href="deleted.jsp" class="btn btn-default btn-sm btn pull-right" role="button" aria-pressed="true"><i class="glyphicon glyphicon-trash"></i></p></a>
                    </div>
                </div>
            </div>
            <br>
            <%
                }
            %>
        </div>
    </body>
</html>