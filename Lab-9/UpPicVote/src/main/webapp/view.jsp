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
</head>
<link href="styles.css" rel="stylesheet">
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

<h1>View pictures</h1>

<%
    List<Image> images = (List<Image>) request.getAttribute("images");
    System.out.println(images.toString());
for (Image image: images) {
    String[] tokens = image.getFilename().split("_");
%>
    <div>
        <h3><%=image.getDescription()%></h3>
        <h4> Posted by: <%=tokens[0]%></h4>
        <img src="Files/<%=image.getFilename()%>">
    </div>
<% } %>


</body>
</html>
