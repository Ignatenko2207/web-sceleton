<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User page</title>
</head>
<body>
<h2>Hello <%=session.getAttribute("user-name")%>!</h2>

</body>
</html>
