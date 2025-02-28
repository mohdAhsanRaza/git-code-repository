<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> FRONT REQUEST HANDLER  </title>
</head>
<body>
<form action="jsp" method="post"> <!-- Add a form tag -->
        <input type="text" name="userId" placeholder="Enter Your ID"> <!-- Use a placeholder for better UX -->
        <input type="submit" value="Fetch Records"> <!-- Corrected the input tag -->
    </form>
</body>
</html>