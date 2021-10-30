<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="회원가입" />
<%@ include file="../common/head.jspf"%>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <form class="table-box-type-1" method="post" action="../member/doJoin">
      <table>
        <colgroup>
          <col width="200" />
        </colgroup>
        <tbody>
          <tr>
            <th>아이디</th>
            <th>
              <input type="text" name="loginId" placeholder="아이디" class="w-96" />
            </th>
          </tr>
          <tr>
            <th>비밀번호</th>
            <th>
              <input type="password" name="loginPw" placeholder="비밀번호" class="w-96" />
            </th>
          </tr>
          <tr>
            <th>비밀번호 확인</th>
            <th>
              <input type="password" name="loginPwConfirm" placeholder="비밀번호 확인" class="w-96" />
            </th>
          </tr>
          <tr>
            <th>이름</th>
            <th>
              <input type="text" name="name" placeholder="이름" class="w-96" />
            </th>
          </tr>
          <tr>
            <th>닉네임</th>
            <th>
              <input type="text" name="nickname" placeholder="닉네임" class="w-96" />
            </th>
          </tr>
          <tr>
            <th>이메일</th>
            <th>
              <input type="text" name="email" placeholder="이메일" class="w-96" />
            </th>
          </tr>
          <tr>
            <th>전화번호</th>
            <th>
              <input type="text" name="cellphoneNo" placeholder="전화번호" class="w-96" />
            </th>
          </tr>
          <tr>
            <th colspan="2">
              <input type="submit" value="회원가입" class="hover:underline" />
            </th>
          </tr>
        </tbody>
      </table>
    </form>
    
    <div class="btns mt-5">
      <button class="hover:underline" type="button" onclick="history.back();">뒤로가기</button>
      <a class="btn-text-link" href="../article/modify?id=${article.id}">게시물 수정</a>
      <a class="btn-text-link" onclick="if (confirm('정말 삭제하시겠습니까?') == false) return false;" href="../article/doDelete?id=${article.id}">게시물 삭제</a>
    </div>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>