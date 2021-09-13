<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MVC Film Site</title>
</head>
<body>
	<h2>
		<b>Film Update Status</b>
	</h2>
	<c:if test="${film.id > 0}">Film ID ${film.id } updated successfully.
	</c:if>
	<a href="home.do">Return Home</a>
</body>
</html>