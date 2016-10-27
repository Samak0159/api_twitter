<%@ page import="java.util.ArrayList" %>
<%@ page import="metier.User" %>
<%@ page import="twitter4j.Status" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Friends Timeline</title>
    </head>
    <body>
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
                        <img style="margin-top:5px;" src="<%out.println(user.getUrlPhoto());%>">
                    </div>
                    <div class="col-sm-10">
                        <strong class="fullname"> <%out.println(user.getNom());%></strong>
                        <!--<span>- @<% //out.print(user.getUsername());%></span>-->
                        <p><%out.println(st.getText());%></p>
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