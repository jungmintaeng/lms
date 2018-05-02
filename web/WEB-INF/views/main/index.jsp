<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cafe24.lms.domain.CategoryType" %>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<c:set var="numberStart" value="${list.totalItems - ((list.curPage-1)*5)}"/>
<!DOCTYPE html>
<html>
<head>
  <title>LMS</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <link href="${cPath}/assets/css/main.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script type="text/javascript" src="${cPath}/assets/js/jquery/jquery-1.9.0.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<script>
    var messageBox = function (title, message, closeFunc) {
        $("#dialog-message").attr("title", title);
        $("#dialog-message p").text(message);
        $("#dialog-message").dialog({
            modal: true,
            buttons: {
                OK: function () {
                    $(this).dialog("close");
                }
            },
            close: closeFunc
        });
    };

    $(function () {
        var message = "${result}";
        if (message == "fail") {
            messageBox("대여 실패", "대여중인 상품입니다.", null);
        }

        $(document).on("click", ".reserve-btn", function () {
            event.preventDefault();
            var targetNo = $(this).attr("data-no");
            $.ajax({
                url: "${cPath}/reserve/" + targetNo,
                type: "get",
                dataType: "json",
                data: "",
                success: function (response) {
                    if (response.data == null) {
                        messageBox("예약 실패", "상품이 대여중이 아니거나 이미 예약 중인 상품입니다.", null);
                    } else {
                        var order = response.data;
                        var startDate = order.borrowDate;
                        var returnDate = order.returnDate;
                        var orderNo = order.orderNo;
                        var message = "";
                        message += "예약에 성공하였습니다.<br><br>";
                        message += "대출일 : " + startDate + "<br>";
                        message += "반납일 : " + returnDate + "<br>";
                        message += "예약 우선 순위 : " + orderNo + "순위<br>";
                        messageBox("예약 정보", message, null);
                    }
                },
                error: function (xhr, status, e) {
                    messageBox("예약 실패", "로그인 해주세요.", null);
                }
            });
            return false;
        });
    });
</script>
<body>
<div id="container">
  <c:import url="/WEB-INF/views/include/header.jsp"/>
  <div id="content">
    <div id="board">
      <form id="search_form" action="cPath" method="get">
        <input type="text" id="kwd" name="kwd" value="">
        <input type="submit" value="찾기">
      </form>
      <table class="tbl-ex">
        <tr>
          <th>번호</th>
          <th>타이틀</th>
          <th>카테고리</th>
          <th>&nbsp;</th>
        </tr>
        <c:forEach items="${list.content}" var="item" varStatus="status">
          <tr>
            <td>${numberStart - status.index}</td>
            <td>${item.title}</td>
            <td>
              <c:choose>
                <c:when test="${item.category.type == CategoryType.BOOK}">
                  [도서]
                </c:when>
                <c:when test="${item.category.type == CategoryType.CD}">
                  [CD]
                </c:when>
                <c:when test="${item.category.type == CategoryType.DVD}">
                  [DVD]
                </c:when>
              </c:choose>
                ${item.category.name}
            </td>
            <td>
              <a href="${cPath}/rent/${item.no}?p=${list.curPage}" class="rent-btn btn">대여</a>
              <a data-no="${item.no}" href="" class="reserve-btn btn">예약</a>
            </td>
          </tr>
        </c:forEach>
      </table>

      <div class="pager">
        <ul>
          <c:choose>
            <c:when test="${list.canGoPrev}">
              <li><a href="${cPath}?p=${list.startNo - 1}">◀</a></li>
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
                <li><a href="${cPath}?p=${p}">${p}</a></li>
              </c:otherwise>
            </c:choose>
          </c:forEach>


          <c:choose>
            <c:when test="${list.canGoNext}">
              <li><a href="${cPath}?p=${list.endNo + 1}">▶</a></li>
            </c:when>
            <c:otherwise>
              <li>▶</li>
            </c:otherwise>
          </c:choose>
        </ul>
      </div>
      <div class="bottom">
      </div>
      <div id="dialog-message" title="" style="display:none">
        <p></p>
      </div>
    </div>
  </div>
  <c:import url="/WEB-INF/views/include/footer.jsp"/>
</div>
</body>
</html>