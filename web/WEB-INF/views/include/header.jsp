<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cafe24.security.Auth.Role" %>

<div id="header">
  <h1>도서대여서비스</h1>
  <ul>
    <c:if test="${empty authUser}">
    <li><a href="${pageContext.servletContext.contextPath }/user/login">로그인</a>
    <li>
    <li><a href="${pageContext.servletContext.contextPath }/user/join">회원가입</a>
    <li>
      </c:if>
      <c:if test="${not empty authUser && authUser.role == 'ADMIN'}">
    <li><a href="${pageContext.servletContext.contextPath }/admin">관리자페이지</a>
    <li>
      </c:if>
      <c:if test="${not empty authUser}">
    <li><a href="${pageContext.servletContext.contextPath }/user/modify">회원정보수정</a>
    <li>
    <li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a>
    <li>
    <li>${authUser.name }님 안녕하세요 !</li>
    </c:if>
  </ul>
</div>