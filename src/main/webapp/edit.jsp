<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Edit a Film</h2>
	<h5>Enter the data below to edit:</h5>
	<form action="updateFilm.do" method="POST">
		Title: <input type="text" name="title" /> <br> Description: <input
			type="text" value = "${film.description }" name="description" /> <br> Release Year: <input
			type="text" name="releaseYear" /> <br> Language ID: <input
			type="text" name="languageID" /> <br> Rental Duration: <input
			type="text" name="rentalDuration" /> <br> Rental Rate: <input
			type="text" name="rentalRate" /> <br> Length: <input
			type="text" name="length" /> <br> Replacement Cost: <input
			type="text" name="replacementCost" /> <br> Rating: <input
			type="text" name="rating" /> <br> Special Features: <input
			type="text" name="specialFeatures" /> <br>
			<input type="submit" value="Update Film"/>
	</form>
</body>
</html>