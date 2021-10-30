<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="게시물 수정" />
<%@ include file="../common/head.jspf"%>

<head>
<link rel="stylesheet" href="/resource/css/article.css" type="text/css">
</head>

<section>
  <div class="article-write-wrap">
    <form method="POST" action="../article/doWrite">
      <input type="hidden" name="id" value="${article.id}" />
      <ul>
        <li><c:if test="${article.boardId == 1}">
          공지사항
        </c:if> <c:if test="${article.boardId == 2}">
          자유
        </c:if></li>
        <li><input type="text" name="title"
          value="${article.title}" /></li>
        <li><textarea rows="10" name="body">${article.body}</textarea></li>
        <li><input type="submit" value="글 수정하기" /></li>
      </ul>
    </form>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>