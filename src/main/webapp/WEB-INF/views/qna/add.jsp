<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Qna Add</h1>
<%-- 	<form action="" method="post" enctype="multipart/form-data">
		<input type="text" name="boardTitle">
		<input type="text" name="boardWriter">
		<textarea rows="" cols="" name="boardContents"></textarea>
		<input type="file" name="attaches">
		<input type="file" name="attaches">
		<input type="file" name="attaches">
		<button>Add</button>
	</form> --%>
	<form:form modelAttribute="qnaVO" enctype="multipart/form-data"> <!-- VO의 setter/getter 이름 -->
		<form:input path="boardWriter"/>
		<form:errors path="boardWriter"></form:errors>
		<form:input path="boardTitle"/>
		<form:errors path="boardTitle"></form:errors>
		<form:textarea path="boardContents"/>
		<input type="file" name="attaches">
		<input type="file" name="attaches">
		<input type="file" name="attaches">
		<form:button>ADD</form:button>
	</form:form> 
</body>
</html>