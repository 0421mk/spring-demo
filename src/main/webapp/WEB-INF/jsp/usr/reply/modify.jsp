<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="댓글 수정" />
<%@ include file="../common/head.jspf"%>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <form class="table-box-type-1" method="POST" action="../reply/doModify">
      <input type="hidden" name="id" value="${reply.id}" />
      <table>
        <colgroup>
          <col width="200" />
        </colgroup>
        <tbody>
          <tr>
            <th>번호</th>
            <td>${reply.id}</td>
          </tr>
          <tr>
          <tr>
            <th>작성날짜</th>
            <td>${reply.getForPrintRegDate()}</td>
          </tr>
          <tr>
            <th>수정날짜</th>
            <td>${reply.getForPrintUpdateDate()}</td>
          </tr>
          <tr>
            <th>작성자</th>
            <td>${reply.extra_writerName}</td>
          </tr>
          <tr>
            <th>내용</th>
           <td>
            <textarea rows="10" name="body" class="w-full">${reply.body}</textarea>
            </td>
          </tr>
          <tr>
            <th colspan="2">
              <input type="submit" value="수정" class="hover:underline" />
            </th>
          </tr>
        </tbody>
      </table>
    </div>
    
    <div class="btns mt-5">
      <button class="hover:underline" type="button" onclick="history.back();">뒤로가기</button>
      <a class="btn-text-link" onclick="if (confirm('정말 삭제하시겠습니까?') == false) return false;" href="../reply/doDelete?id=${reply.id}">게시물 삭제</a>
    </div>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>