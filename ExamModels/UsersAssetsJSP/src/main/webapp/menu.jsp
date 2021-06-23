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

    <div id="assetAdditionDiv">
        <h2>Add an asset</h2>
        Name: <input type="text" name="name" id="nameInput">
        Description: <input type="text" name="description" id="descriptionInput"> <br>
        Value: <input type="text" name="value" id="valueInput"> <br>
        <input type="submit" value="Add" id="assetAdditionButton"/>
    </div>

<ul id="assetsList">

    </ul>
<%  } %>
</body>
</html>
