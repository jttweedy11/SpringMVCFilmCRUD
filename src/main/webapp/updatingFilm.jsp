<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Updating a film:</h3>

	<form action="updatingFilm.do?filmId=${film.id }" method="POST">
		Title: <input type="text" value="${film.title }" name="title" /> <br> 
		Description: <input type="text" value="${film.description }" name="description" /> <br>
		Release Year: <input type="text" value ="${film.releaseYear }" name="releaseYear" /> <br>
		Language ID: <input type="text" value="${film.languageID }" name="languageID" /> <br> 
		Rental Duration: <input type="text" value="${film.rentalDuration }" name="rentalDuration" /> <br>
		Rental Rate: <input type="text" value="${film.rentalRate }" name="rentalRate" /> <br>
		Length: <input type="text" value="${film.length }" name="length" /> <br> 
		Replacement Cost <input type="text" value="${film.replacementCost }" name="Replacement" /> <br> 
		Rating:	<input type="text" value="${film.rating }" name="rating" /> <br> 
		Special Features: <input type="text" value="${film.specialFeatures }" name="specialFeatures" /> <br>
		 <input type="submit" value="Update Film" />
	</form>

</body>
</html>