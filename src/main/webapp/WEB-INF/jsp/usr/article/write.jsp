<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="게시물 작성" />
<%@ include file="../common/head.jspf"%>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <form class="table-box-type-1" method="POST"
      action="../article/doWrite">
      <input type="hidden" name="id" value="${article.id}" />
      <table>
        <colgroup>
          <col width="200" />
        </colgroup>
        <tbody>
          <tr>
            <th>작성자</th>
            <td>
              ${rq.loginedMember.nickname}
            </td>
          </tr>
          <tr>
            <th>게시판</th>
            <td>
              <select name="boardId" id="">
                <option value="1">공지사항</option>
                <option value="2">자유게시판</option>
              </select>
            </td>
          </tr>
          <tr>
            <th>제목</th>
            <td><input type="text" name="title" class="w-full"
              value="" placeholder="제목" /></td>
          </tr>
          <tr>
            <th>내용</th>
            <td><textarea rows="10" name="body" class="w-full"></textarea>
            </td>
          </tr>
          <tr>
            <th colspan="2"><input type="submit" value=글쓰기
              class="hover:underline" /></th>
          </tr>
        </tbody>
      </table>
  </div>

  <div class="btns mt-5">
    <button class="hover:underline" type="button"
      onclick="history.back();">뒤로가기</button>
  </div>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>