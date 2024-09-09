<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<spring:message code="board.name"></spring:message>
<br></br>
	<table>
		<thead>
			<tr>
				<th>Num</th>
				<th>Title</th>
				<th>Writer</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="vo">
				<tr>
					<th>${vo.boardNum}</th>
					<th><a href="./detail?boardNum=${vo.boardNum}">${vo.boardTitle}</a></th>
					<th>${vo.boardWriter}</th>
					<th>${vo.createDate}</th>
				</tr>
			</c:forEach>
		</tbody>		
	</table>
	
	<a href="/qna/add"><button>Add List</button></a>
</body>
</html>