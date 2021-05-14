<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<head>
    <title>BlackJack</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script>
        function validateForm(move) {

            var request = new XMLHttpRequest();
            request.open('GET', '/jack/possibleMoves', false);  // `false` makes the request synchronous
            request.send(null);
            var resp = request.response.toString();
            if (resp.includes(move)){
                return true;
            }
            alert("Move not possible: possible moves: " + resp);
            return false;
        }
    </script>

</head>
<body>
<div class="container-fluid py-5">
<h1 class="display-5 fw-bold">
<% out.print(request.getSession().getAttribute("heading")); %>
</h1>
</div>
<h2><% out.print(request.getSession().getAttribute("description"));%></h2>
<p>
<% if (request.getSession().getAttribute("computerHandString") != null){
    out.print(request.getSession().getAttribute("computerHandString"));
}; %>
</p>
<p>
    <% if (request.getSession().getAttribute("userHandString") != null){
        out.print(request.getSession().getAttribute("userHandString"));

    }; %>
</p>

<form action='${pageContext.request.contextPath}/jack/start'  method='post'><input type='submit' value='Start new game'></form>
<form action='${pageContext.request.contextPath}/jack/move/stand' onsubmit="return validateForm('stand')" method='post'><input type='submit' value='Stand'></form>
<form action='${pageContext.request.contextPath}/jack/move/hit' onsubmit="return validateForm('hit')" method='post'> <input type='submit' value='Hit'></form>
<form action='${pageContext.request.contextPath}/jack/won' onsubmit="return validateForm('won')" method='post'><input type='submit' value='See winner'></form>
<form action='${pageContext.request.contextPath}/jack/stats' method='get'><input type='submit' value='See game stats'></form>

</body>
</html>
