<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<c:forEach items="${searchFriendsResults}" var="result">
		<form:form method="POST" action="${contextPath}/sendFriendRequest" modelAttribute="user">
			<p>${result.name}</p>
			<spring:bind path="user">
				<input type="hidden" value="${result}" name="user">
			</spring:bind>
			<button type="submit">Send friend request</button>
		</form:form>
	</c:forEach>
</body>
</html>