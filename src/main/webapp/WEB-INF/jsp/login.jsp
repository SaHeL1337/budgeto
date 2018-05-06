<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Test Hexagon</title>
<link href="../resources/style.css" rel="stylesheet" />
</head>
<body>
		<div class="header">
			<div>sahsec<span>.com</span></div>
		</div>
		<br>
		<c:if test="${!empty message}">
				<div id="messageContainerError">
				${ message } 
				</div> 
			</c:if> 
		<div class="login">
			
			<form action="login" method="POST">
				<input class="inputSmall" type="text" placeholder="username" name="username"><br>
				<input class="inputSmall" type="password" placeholder="password" name="password"><br>
				<input class="submitSmall" type="submit" value="Login">
			</form>
			
		</div>
</body>
</html>