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
        <a href="${contextPath }" class="navbar-brand">SpringBook</a>
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

                <!--- \\\\\\\Post-->
                <form:form method="POST" action="${contextPath}/post" modelAttribute="postForm">
                <div class="card gedf-card post-item">
                    <div class="card-header">
                        <ul class="nav nav-tabs card-header-tabs" id="myTab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="posts-tab" data-toggle="tab" href="#posts" role="tab" aria-controls="posts" aria-selected="true">Make
                                    a publication</a>
                            </li>
                           <!--  <li class="nav-item">
                                <a class="nav-link" id="images-tab" data-toggle="tab" role="tab" aria-controls="images" aria-selected="false" href="#images">Images</a>
                            </li>--> 
                        </ul>
                    </div>
                    <div class="card-body">
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade show active" id="posts" role="tabpanel" aria-labelledby="posts-tab">
                                <div class="form-group">
                                    <label class="sr-only" for="message">post</label>
                                    <spring:bind path="text">
                                    	<form:textarea class="form-control" id="message" rows="3" placeholder="What are you thinking?" type="text" path="text"></form:textarea>
                                    </spring:bind>
                                </div>

                            </div> 
                        </div>
                        <div class="btn-toolbar justify-content-between">
                            <div class="btn-group">
                                <button type="submit" class="btn btn-primary">Post</button>
                            </div>
        
                        </div>
                    </div>
                </div>
                </form:form>
                <!-- Post /////-->

                <!--- \\\\\\\Post-->
                <c:forEach items="${postFeed}" var="postItem" varStatus="status">
                <div class="card gedf-card post-item">
                    <div class="card-header">
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="ml-2">
                                    <div class="h5 m-0">@${postItem.user.name}</div>
                                </div>
                            </div>
                            <div></div>
                        </div>

                    </div>
                    <div class="card-body">
                        <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"></i><fmt:formatDate value="${postItem.updatedAt}" pattern="dd.MM.yy, HH:mm" /></div>
                        <p class="card-text">
                            ${postItem.text}
                        </p>
                    </div>
                    <div class="card-footer">
                        <button class="btn btn-primary" onclick="openForm(${postItem.id})"><i class="fa fa-comment"></i> Comment</button>
                        <div class="card-body" style="display: none;" id="${postItem.id }">
                        <form:form method="POST" action="${contextPath}/comment" modelAttribute="commentForm_${status.index}" id="${postItem.id}_form">
	                        <div class="tab-content" id="myTabContent">
	                            <div class="tab-pane fade show active" id="posts" role="tabpanel" aria-labelledby="posts-tab">
	                                <div class="form-group">
	                                    <label class="sr-only" for="message">post</label>
	                                    <spring:bind path="text">
	                                    	<form:textarea class="form-control" id="message" rows="3" 
	                                    				placeholder="Comment..." type="text" path="text"></form:textarea>
	                                    </spring:bind>
	                                    <spring:bind path="postId">
	                                    	<form:input type="hidden" value="${postItem.id}" path="postId"/>
	                                    </spring:bind>
	                                </div>
	                            </div>
	                        </div>
	                        <button type="submit" class="btn btn-primary">Add Comment</button>

                        </form:form>
                        <button class="btn btn-primary" onclick="closeForm(${postItem.id })">Cancel</button>
                    </div>
                    </div>
                    <c:forEach items="${postItem.comments}" var="comment">
                    <div class="card" style="width: 100%; padding-left:10%;">
  						<div class="card-body">
    					<h6 class="card-title">@${comment.user.name}</h6>
    					<div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"></i><fmt:formatDate value="${comment.createdAt}" pattern="dd.MM.yy, HH:mm" /></div>
    					<p class="card-text">${comment.text}</p>
				  		</div>
						</div>
                    </c:forEach>
                </div>
                </c:forEach>
                <!-- Post /////-->
        </div>
    </div>
     	<script>
				function openForm(element) {
				  document.getElementById(element).style.display = "block";
				}
				
				function closeForm(element) {
				  document.getElementById(element).style.display = "none";
				  document.getElementById(element + "_form").reset();
				}
		</script>
</html>