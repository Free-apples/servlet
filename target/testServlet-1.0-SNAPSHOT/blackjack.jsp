<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<head>
    <title>BlackJack</title>
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
            // if (request.status === 200) {
            //     var resp = request.response.toString();
            //     if (move in resp) {
            //         alert(resp)
            //         return true;
            //     }
            // }
            // alert(request.response.toString());
            // return false;
        }
    </script>

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

<%--<form action='${pageContext.request.contextPath}/jack/move/stand' onsubmit="return validateForm('stand')" method='post'><input type='submit' value='Stand'></form>--%>
<%--<form id="myForm" action='${pageContext.request.contextPath}/jack/move/stand' onsubmit="return validateForm('stand')" method='post'><input type='submit' value='TestStand'></form>--%>
<%--<script>--%>
<%--test = validateForm("move");--%>
<%--alert(test)</script>--%>

<%--<% out.print(request.getSession().getAttribute("button")); %>--%>
<form action='${pageContext.request.contextPath}/jack/start'  method='post'><input type='submit' value='Start new game'></form>
<form action='${pageContext.request.contextPath}/jack/move/stand' onsubmit="return validateForm('stand')" method='post'><input type='submit' value='Stand'></form>
<form action='${pageContext.request.contextPath}/jack/move/hit' onsubmit="return validateForm('hit')" method='post'> <input type='submit' value='Hit'></form>
<form action='${pageContext.request.contextPath}/jack/won' onsubmit="return validateForm('won')" method='post'><input type='submit' value='See winner'></form>
<form action='${pageContext.request.contextPath}/jack/stats' method='get'><input type='submit' value='See game stats'></form>

</body>
</html>
