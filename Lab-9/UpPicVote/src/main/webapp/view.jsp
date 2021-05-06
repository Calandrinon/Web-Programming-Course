<%@ page import="java.util.List" %><%--
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
    List<String> filenames = (List<String>) request.getAttribute("filenames");
    System.out.println(filenames.toString());
for (String filename: filenames) {
%>
    <div>
        <h3><%=filename%></h3>
        <img src="Files/<%=filename%>">
    </div>
<% } %>


</body>
</html>
