<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Authorization page</title>
</head>
<body>
<h2>Hello user!</h2>
<h2>Your login and password are wrong, please, try again!</h2>
<br/>
<br/>
<form action="authorization" method="post">
    <br/>
    <input type="text" name="login" placeholder="input login" size="40">
    <br/>
    <input type="password" name="password" placeholder="input password" size="40">
    <br/>
    <input type="submit" value="SEND TO SERVER">
</form>
</body>
</html>
