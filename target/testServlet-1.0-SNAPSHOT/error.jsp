<%--
  Created by IntelliJ IDEA.
  User: meganfreedman
  Date: 5/12/21
  Time: 9:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>Error</h1>
<% out.print(request.getSession().getAttribute("errorMessage")); %>
<a href='${pageContext.request.contextPath}/jack/start' class=button> Start new game</a>

</body>
</html>
