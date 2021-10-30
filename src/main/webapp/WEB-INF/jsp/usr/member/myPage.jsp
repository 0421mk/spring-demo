<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="마이페이지" />
<%@ include file="../common/head.jspf"%>

<head>
<link rel="stylesheet" href="/resource/css/member.css" type="text/css">
</head>

<section>
  <div class="mypage-wrap">
    <div class="title">마이페이지</div>
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
        <div class="right">${rq.loginedMember.nickname}</div>
      </li>
      <li>
        <div class="left">이메일</div>
        <div class="right">${rq.loginedMember.email}</div>
      </li>
      <li>
        <div class="left">전화번호</div>
        <div class="right">${rq.loginedMember.email}</div>
      </li>
      <li>
        <button type="button" onclick="history.back();">뒤로가기</button> <a
        href="../member/checkPassword">회원정보 수정</a>
      </li>
    </ul>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>