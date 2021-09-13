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

	<h3>
		<b>Search results(${films.size()}):</b>
	</h3>
	<c:if test="${not empty films }">
		<div>
			<c:forEach var="f" items="${films }">
				<div>
					<input type="radio" id="${f.id }" name="filmId" value="${f.id }">
					ID: ${f.id }<br>
					Title: ${film.title } <label>${f.title}</label><br>
					Description: ${f.description }<br>
				</div>
			</c:forEach>
			<input type="submit" value="More Info">
		</div>
	</c:if>
	<a href="home.do">Return Home</a>


</body>
</html>