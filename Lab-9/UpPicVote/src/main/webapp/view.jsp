<%@ page import="java.util.List" %>
<%@ page import="com.uppicvote.model.Image" %><%--
  Created by IntelliJ IDEA.
  User: calandrinon
  Date: 4/23/21
  Time: 9:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Testing Java Servlets</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="main.js"></script>
</head>
<body>
<nav class="navbar">
    <ul>
        <li>
            <a href="/upload.jsp" class="menu_link"><div class="wrap"><span class="text">Upload a picture</span></div></a>
        </li>
        <li>
            <a href="/pictures" class="menu_link"><div class="wrap"><span class="text">View other pictures</span></div></a>
        </li>
    </ul>
</nav>

<%  String username = (String) session.getAttribute("username");
    if (username != null) {
        System.out.println("Welcome, "+ username + "!");
%>

<div id="imageContainer">
    <h3>Pick the number N for displaying the top N pictures by rating: <input type="text" id="numberTopN"> <button id="topNButton">Get the top N posts</button></h3>
    <div id="postContainer">
    <%
        List<Image> images = (List<Image>) request.getAttribute("images");
        System.out.println(images.toString());
    for (Image image: images) {
        String[] tokens = image.getFilename().split("_");
    %>
    <div id="post">
        <img src="Files/<%=image.getFilename()%>">
        <h4> Description: <%=image.getDescription()%></h4>
        <h4> Rating: <h4 id="rating_<%=image.getFilename()%>"><%=image.getNumberOfVotes()%></h4></h4>
        <h4> Posted by: <%=tokens[0]%></h4>
        <button class="upvoteButton greyButtons" type="button" id="upvote_<%=image.getFilename()%>">^</button>
        <button class="downvoteButton greyButtons" type="button" id="downvote_<%=image.getFilename()%>">v</button>
        <h4 id="response_<%=image.getFilename()%>"></h4>
    </div>
    <% } %>
    </div>
</div>

<%  } else { %>
<h1 style="color: red;"> You need to log into your account before viewing images! </h1>
<%  }  %>


</body>
</html>
