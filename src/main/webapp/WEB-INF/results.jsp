<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
</head>
<body>
  <c:choose>
    <c:when test="${! empty film}">
      <ul>
        <li>${film.id}</li>
      </ul>
    </c:when>
    <c:otherwise>
      <p>No Movie Found</p>
    </c:otherwise>
  </c:choose>
</body>
</html>