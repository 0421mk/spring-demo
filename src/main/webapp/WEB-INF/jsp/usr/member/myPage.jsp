<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="마이페이지" />
<%@ include file="../common/head.jspf"%>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <div class="table-box-type-1">
      <table>
        <colgroup>
          <col width="200" />
        </colgroup>
        <tbody>
          <tr>
            <th>로그인 아이디</th>
            <th>
              ${rq.loginedMember.loginId}
            </th>
          </tr>
          <tr>
            <th>이름</th>
            <th>
              ${rq.loginedMember.name}
            </th>
          </tr>
          <tr>
            <th>별명</th>
            <th>
              ${rq.loginedMember.nickname}
            </th>
          </tr>
          <tr>
            <th>이메일</th>
            <th>
              ${rq.loginedMember.email}
            </th>
          </tr>
          <tr>
            <th>전화번호</th>
            <th>
              ${rq.loginedMember.cellphoneNo}
            </th>
          </tr>
          <tr>
            <th colspan="2">
              <a href="../member/checkPassword" class="btn btn-primary">회원정보 수정</a>
              <button type="button" class="btn btn-outline btn-secondary" onclick="history.back();">뒤로가기</button>
            </th>
          </tr>
        </tbody>
      </table>
    </div>
    
    <div class="btns mt-5">
      <button class="hover:underline" type="button" onclick="history.back();">뒤로가기</button>
      <a class="btn-text-link" href="../article/modify?id=${article.id}">게시물 수정</a>
      <a class="btn-text-link" onclick="if (confirm('정말 삭제하시겠습니까?') == false) return false;" href="../article/doDelete?id=${article.id}">게시물 삭제</a>
    </div>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>