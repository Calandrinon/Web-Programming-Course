<%--
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
        <li>
          <a href="/" class="menu_link" id="logoutLink"><div class="wrap"><span class="text">Logout</span></div></a>
        </li>
        <%  String username = (String) session.getAttribute("username");
          if (username == null) {
        %>
        <li>
          <a href="/" class="menu_link"><div class="wrap"><span class="text">Login</span></div></a>
        </li>
        <% } %>
      </ul>
    </nav>

    <h1>Welcome!</h1>
  </body>
</html>
