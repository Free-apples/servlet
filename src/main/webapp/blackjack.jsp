<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<head>
    <title>BlackJack</title>
</head>
<body>
<h1>
<% out.print(request.getSession().getAttribute("heading")); %>
</h1>
<h2><% out.print(request.getSession().getAttribute("description"));%></h2>
<p>
<% if (request.getSession().getAttribute("computerHandString") != null){
    out.print(request.getSession().getAttribute("computerHandString"));
}; %>
</p>
<p>
    <% if (request.getSession().getAttribute("userHandString") != null){
        out.print("user hand" + request.getSession().getAttribute("userHandString"));

    }; %>
</p>
<% out.print(request.getSession().getAttribute("button")); %>

</body>
</html>
