<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="마이페이지" />
<%@ include file="../common/head.jspf"%>

<head>
<link rel="stylesheet" href="/resource/css/member.css" type="text/css">
</head>

<section>
  <div class="member-modify-wrap">
    <form method="post" action="../member/doModify">
      <div class="title">회원정보 변경</div>
      <ul>
        <li>
          <div class="left">아이디</div>
          <div class="right">${rq.loginedMember.loginId}</div>
        </li>
        <li>
          <div class="left">이름</div>
          <div class="right">${rq.loginedMember.name}</div>
        </li>
        <li>
          <div class="left">닉네임</div>
          <div class="right">
            <input type="text" name="nickname"
              placeholder="${rq.loginedMember.nickname}" class="w-96" />
          </div>
        </li>
        <li>
          <div class="left">이메일</div>
          <div class="right">${rq.loginedMember.email}</div>
        </li>
        <li>
          <div class="left">전화번호</div>
          <div class="right">${rq.loginedMember.email}</div>
        </li>
        <li class="submit-wrap">
          <input type="submit" value="회원정보 변경"/>
        </li>
        <li>
          <button type="button" onclick="history.back();">뒤로가기</button>
          <a href="../member/modifyPw">비밀번호 변경</a>
        </li>
      </ul>
    </form>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>