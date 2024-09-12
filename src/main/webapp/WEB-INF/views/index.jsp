<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<h1>home</h1>
	<spring:message code="hello"></spring:message>
	<br></br>
	<spring:message code="hello2" text="기본값"></spring:message>
	<br></br>
	
	<img alt="" src="">
	<a href="./qna/list"><button>list</button></a>
	<a href="./qna/add"><button>Add list</button></a>
	<a href="./member/add"><button>add</button></a>
		
	<a href="./member/login"><button>login</button></a>
	<a href="./member/logout"><button>logout</button></a>
	
	<sec:authorize access="!isAuthenticated()">
		<h1>Login 하기 전</h1>
		<a href="/member/login">Login</a>
		<a href="/oauth2/authorization/kakao">kakao Login</a>
	</sec:authorize>
	
	
	<sec:authorize access="isAuthenticated()">
	<a href="./member/mypage"><button>mypage</button></a>
		<h1>Login 성공</h1>
		<sec:authentication property="principal" var="member"/>
		<spring:message code="member.login.message" arguments="${member.username}-${member.email}" argumentSeparator="-"></spring:message>
		
		<c:forEach items="${member.vos}" var="r">
		<h1>${r.roleName}</h1>
		</c:forEach>
	</sec:authorize>
	
	<sec:authorize access="hasRole('ADMIN')">
		<h1>관리자 전용</h1>
	</sec:authorize>
</body>
</html>