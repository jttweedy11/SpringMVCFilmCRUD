<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Movie</title>
</head>
<body>
	<h1>Film Details</h1>
	<c:choose>
		<c:when test="${! empty film}">
			<ul>
				<li><b>Film ID: </b>${film.id}</li>
				<li><b>Title: </b>${film.title}</li>
				<li><b>Description: </b>${film.description}</li>
				<li><b>Year Released: </b>${film.releaseYear}</li>
				<li><b>Rating: </b>${film.rating}</li>
				<li><b>Actors: </b></li>
				<c:forEach items="${film.actors}" var="item">
    ${item}<br>
				</c:forEach>
			</ul>

			<a href="filmToUpdate.do?filmId=${film.id}">Edit Film</a>
			<a href="deleteFilm.do?filmId=${film.id}">Delete Film</a>


		</c:when>
		<c:otherwise>
			<p>No Movie Found</p>
			<a href="home.do">Return Home</a>
		</c:otherwise>
	</c:choose>
</body>
</html>