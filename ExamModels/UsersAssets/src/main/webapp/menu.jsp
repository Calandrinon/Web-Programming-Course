<%@ page import="com.exam.model.Asset" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="main.js"></script>
</head>
<body>
<%  String username = (String) session.getAttribute("username");
    if (username == null) {
%>
    <h1>You need to log into your account before viewing content.</h1>
<%  } else { %>
    <h1>Welcome, <%=username%>!</h1>
    <ul id="assetsList">

    </ul>
<%  } %>
</body>
</html>
