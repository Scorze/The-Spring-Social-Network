<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<c:forEach items="${searchFriendsResults}" var="result">
		<form method="POST" action="${contextPath}/sendFriendRequest">
			<p>${result.name}</p>
				<input type="hidden" value="${result.name}" name="user">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<button type="submit">Send friend request</button>
		</form>
	</c:forEach>
</body>
</html>