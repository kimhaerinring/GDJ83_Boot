<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<h1>home</h1>
	<img alt="" src="">
	<a href="./qna/list"><button>list</button></a>
	<a href="./member/add"><button>add</button></a>
	<a href="./member/login"><button>login</button></a>
	<a href="./member/logout"><button>logout</button></a>
	
	<c:if test="${empty member}">
		<h1>Login 하기 전</h1>
	</c:if>
	
	<c:if test="${not empty member}">
		<h1>Login 성공</h1>
		<c:forEach items="${member.vos}" var="r">
		<h1>${r.roleName}</h1>
		</c:forEach>
	</c:if>
</body>
</html>