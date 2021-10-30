<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${board.name}" />
<%@ include file="../common/head.jspf"%>

<head>
<link rel="stylesheet" href="/resource/css/article.css" type="text/css">
</head>

<section>
  <ul class="tag-wrap">
    <li><a href="/usr/article/list?boardId=1"><span>#공지사항</span>
    </a></li>
    <li><a href="/usr/article/list?boardId=2"><span>#자유</span>
    </a></li>
    <li><a href="/usr/article/list"><span>#전체</span> </a></li>
  </ul>
  <ul class="article-header">
    <li><a href="/usr/article/list?boardId=${board.id}"><span>${board.name == null ? '전체' : board.name}(${articleCount})</span>
    </a></li>
    <li><a href="/usr/article/write"><span>글쓰기</span> </a></li>
  </ul>
  <!--  <div class="search-wrap">
      <form action="">
        <input type="hidden" name="boardId" value="${param.boardId}" />
        <select name="searchKeywordTypeCode" id="">
          <option value="title">제목</option>
          <option value="body">내용</option>
          <option value="default">제목, 내용</option>
        </select> <input type="text" name="searchKeyword"
          value="${param.searchKeyword}" placeholder="검색어를 입력해주세요." />
        <button type="submit">검색</button>
      </form>
    </div>  -->
  <div class="article-wrap">
    <c:forEach var="article" items="${articles}">
      <div class="article">
        <div class="article-title-wrap">
          <a href="../article/detail?id=${article.id}">${article.title}</a>
        </div>
        <div class="article-body-wrap">
          <a href="../article/detail?id=${article.id}">${article.body}</a>
        </div>
        <div class="article-info-wrap">
          <ul>
            <li><a
              href="/usr/article/list?boardId=${article.boardId}">
              <c:if test="${article.boardId == 1}">
                  공지사항
              </c:if>
              <c:if test="${article.boardId == 2}">
                  자유
              </c:if>
            </a></li>
            <li>${article.getForPrintRegDate()}</li>
            <li>${article.extra_writerName}</li>
            <li><i class="far fa-thumbs-up fa-1x"></i>
              ${article.extra_likePoint}</li>
          </ul>
        </div>
      </div>
    </c:forEach>
    <div class="page-wrap">
      <div class="container">
        <c:set var="pageMenuArmLen" value="1" />
        <c:set var="startPage"
          value="${page - pageMenuArmLen >= 1 ? page - pageMenuArmLen : 1 }" />
        <c:set var="endPage"
          value="${page + pageMenuArmLen >= pagesCount ? pagesCount : page + pageMenuArmLen }" />

        <c:set var="baseUri"
          value="boardId=${board.id}&searchKeywordTypeCode=${param.searchKeywordTypeCode}&searchKeyword=${param.searchKeyword}" />

        <c:if test="${startPage > 1}">
          <a class="left-btn" href="?page=1&${baseUri}"><i
            class="fas fa-chevron-left"></i><i
            class="fas fa-chevron-left"></i></a>
        </c:if>
        <c:forEach begin="${startPage}" end="${endPage}" var="i">
          <a class="btn ${page == i ? 'btn-active' : '' }"
            href="?page=${i}&${baseUri}">${i}</a>
        </c:forEach>
        <c:if test="${endPage < pagesCount}">
          <a class="right-btn" href="?page=${pagesCount}&${baseUri}"><i
            class="fas fa-chevron-right"></i><i
            class="fas fa-chevron-right"></i></a>
        </c:if>
      </div>
    </div>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>