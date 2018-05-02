<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.cafe24.lms.domain.CategoryType" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<c:set var="numberStart" value="${list.totalItems - ((list.curPage-1)*5)}"/>
<!DOCTYPE html>
<html>
<head>
<title>lms</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/admin/rent.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/admin/include/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>타이틀</th>
						<th>대여자명</th>
						<th>카테고리</th>
						<th>대여일</th>
						<th>반납일</th>
					</tr>
					<c:forEach items="${list.content}" var="item" varStatus="status">
						<tr>
							<td>${numberStart - status.index}</td>
							<td>${item.item.title}</td>
							<td>${item.user.name}</td>
							<td>
								<c:choose>
									<c:when test="${item.item.category.type == CategoryType.BOOK}">
										[도서]
									</c:when>
									<c:when test="${item.item.category.type == CategoryType.CD}">
										[CD]
									</c:when>
									<c:when test="${item.item.category.type == CategoryType.DVD}">
										[DVD]
									</c:when>
								</c:choose>
									${item.item.category.name}
							</td>
							<td>${item.borrowDate}</td>
							<td>${item.returnDate}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${list.canGoPrev}">
								<li><a href="${cPath}/admin/rent?p=${list.startNo - 1}">◀</a></li>
							</c:when>
							<c:otherwise>
								<li>◀</li>
							</c:otherwise>
						</c:choose>


						<c:forEach items="${list.pageNumbers}" var="p">
							<c:choose>
								<c:when test="${p == list.curPage}">
									<li class="selected">${p}</li>
								</c:when>
								<c:when test="${p > list.totalPages || p < 1}">
									<li>${p}</li>
								</c:when>
								<c:otherwise>
									<li><a href="${cPath}/admin/rent?p=${p}">${p}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>


						<c:choose>
							<c:when test="${list.canGoNext}">
								<li><a href="${cPath}/admin/rent?p=${list.endNo + 1}">▶</a></li>
							</c:when>
							<c:otherwise>
								<li>▶</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
			<c:import url="/WEB-INF/views/admin/include/navigation.jsp">
				<c:param name="menu" value="rent"/>
			</c:import>
		</div>
	</div>
</body>
</html>