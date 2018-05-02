<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="${pageContext.servletContext.contextPath }/user/modify">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${user.name }">

					<label class="block-label">이메일</label>
					<h3>${user.email }</h3>
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<c:choose>
							<c:when test='${user.gender == "M" }'>
								<label>여</label> <input type="radio" name="gender" value="F">
								<label>남</label> <input type="radio" name="gender" value="M" checked="checked">
							</c:when>
							<c:otherwise>
								<label>여</label> <input type="radio" name="gender" value="F" checked="checked">
								<label>남</label> <input type="radio" name="gender" value="M">
							</c:otherwise>
						</c:choose>
					</fieldset>
					<input type="submit" value="수정하기">
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>