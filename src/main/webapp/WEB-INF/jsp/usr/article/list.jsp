<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${board.name}" />
<%@ include file="../common/head.jspf"%>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <div>게시물 수 : ${articleCount}</div>
    <div class="table-box-type-1">
      <table>
        <colgroup>
          <col width="50" />
          <col width="150" />
          <col width="150" />
          <col width="150" />
        </colgroup>
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
              <td>${article.writerName}</td>
              <td><a href="../article/detail?id=${article.id}"
                class="btn-text-link">${article.title}</a></td>
            </tr>
          </tbody>
        </c:forEach>
      </table>
    </div>

    <div class="page-wrap mt-3">
      <div class="btn-group flex justify-center">
        <c:set var="pageMenuArmLen" value="3" />
        <c:set var="startPage"
          value="${page - pageMenuArmLen >= 1 ? page - pageMenuArmLen : 1 }" />
        <c:set var="endPage"
          value="${page + pageMenuArmLen >= pagesCount ? pagesCount : page + pageMenuArmLen }" />
        <c:set var="pageMenuArmLen" value="9" />
        
        <c:if test="${startPage > 1}">
           <a class="btn btn-sm" href="?page=${pagesCount}&boardId=${board.id}&searchKeywordTypeCode=${searchKeywordTypeCode}&searchKeyword=${searchKeyword}">◀◀</a>
        </c:if>
        <c:forEach begin="${startPage}" end="${endPage}" var="i">
          <a class="btn btn-sm ${page == i ? 'btn-active' : '' }"
            href="?page=${pagesCount}&boardId=${board.id}&searchKeywordTypeCode=${searchKeywordTypeCode}&searchKeyword=${searchKeyword}">${i}</a>
        </c:forEach>
        <c:if test="${endPage < pagesCount}">
           <a class="btn btn-sm" href="?page=${pagesCount}&boardId=${board.id}&searchKeywordTypeCode=${searchKeywordTypeCode}&searchKeyword=${searchKeyword}">▶▶</a>
        </c:if>
      </div>
    </div>

  </div>
</section>

<%@ include file="../common/foot.jspf"%>