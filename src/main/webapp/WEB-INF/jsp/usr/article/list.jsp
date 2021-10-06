<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="게시물 리스트" />
<%@ include file="../common/head.jspf"%>

<table border="1">
  <c:forEach var="article" items="${articles}">
    <thead>
      <tr>
        <th>번호</th>
        <th>작성날짜</th>
        <th>수정날짜</th>
        <th>작성자</th>
        <th>제목</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${article.id}</td>
        <td>${article.regDate.substring(2, 16)}</td>
        <td>${article.updateDate.substring(2, 16)}</td>
        <td>${article.memberId}</td>
        <td><a href="../article/detail?id=${article.id}">${article.title}</a>
        </td>
      </tr>
    </tbody>
  </c:forEach>
</table>

<%@ include file="../common/foot.jspf"%>