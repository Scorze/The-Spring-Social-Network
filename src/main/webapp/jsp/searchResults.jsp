<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SpringBook</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <link href="${contextPath}/resources/styles/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<nav class="navbar navbar-light bg-white">
        <a href="./" class="navbar-brand">SpringBook</a>
        <form method="GET" action="${contextPath}/searchResults" class="search-bar">
            <div class="input-group ">
                <input type="text" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" name="searchName"/>
                <div class="input-group-append">
                    <button class="btn btn-outline-primary" type="submit" id="button-addon2">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </div>
        </form>
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <button class="btn btn-info" onclick="document.forms['logoutForm'].submit()">Logout</button>
    </nav>


    <div class="container-fluid gedf-wrapper">
        <div class="row">
            <div class="col-md-3">
                <div class="card">
                    <div class="card-body">
                        <div class="h5">@${pageContext.request.userPrincipal.name}</div>
                        <div class="h7 text-muted">Welcome ${pageContext.request.userPrincipal.name}</div>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <div class="h6 text-muted">Friends</div>
                            <c:forEach items="${friends}" var="friend">
                            	<div class="h5">@${friend.name}</div>
                            </c:forEach>
                        </li>
                        <li class="list-group-item">
                            <div class="h6 text-muted">Friend Requests</div>
                            <c:forEach items="${friendRequests}" var="friendRequest">
                                <div class="flex-box">
	                            	<div class="h5" class="request-title">@${friendRequest.requestFrom.name}</div>
	                           		<form method="POST" action="${contextPath}/acceptFriendRequest">
										<input type="hidden" value="${friendRequest.requestFrom.name}" name="user">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
										<button type="submit" class="btn btn-primary request-button">Accept request</button>
				                    </form>
				                    <form method="POST" action="${contextPath}/declineFriendRequest">
										<input type="hidden" value="${friendRequest.requestFrom.name}" name="user">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
										<button type="submit" class="btn btn-primary request-button">Decline request</button>
				                    </form>
			                    </div>
                            </c:forEach>
                        </li>
                        <li class="list-group-item">
                            <div class="h6 text-muted">My Requests</div>
                            <c:forEach items="${myRequests}" var="friendRequest">
                            	<div class="flex-box">
	                            	<div class="h5" class="request-title">@${friendRequest.requestTo.name}</div>
	                           		<form method="POST" action="${contextPath}/cancelFriendRequest">
										<input type="hidden" value="${friendRequest.requestTo.name}" name="user">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
										<button type="submit" class="btn btn-primary request-button">Cancel request</button>
				                    </form>
			                    </div>
                            </c:forEach>
                        </li>
                    </ul>
                </div>
            </div>
            
            <div class="col-md-6 gedf-main">
				<c:forEach items="${searchFriendsResults}" var="result">
				<div class="card gedf-card post-item" style="border: 0px;">
					<div class="card-header" style="background-color:white !important; border-bottom: 0px !important;">
	                  <div class="d-flex justify-content-between align-items-center">
	                     <div class="d-flex justify-content-between align-items-center" style="width:100% !important;">
	                        <div class="ml-2" style="width:100% !important;">
	                            <div class="h5 m-0" style="width:100% !important;">
	                               <form method="POST" action="${contextPath}/sendFriendRequest">
										<div style="width:50%; float:left; text-align:left; vertical-align:center;">@${result.name}</div>
										<input type="hidden" value="${result.name}" name="user">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
										<button type="submit" class="btn btn-primary" style="float:right;">Send friend request</button>
			                       </form>
			                   </div>
	                       </div>
	                    </div>
	               	 </div>
				  </div>
			   </div>
			  </c:forEach>
        	</div>
    </div></div>
</html>