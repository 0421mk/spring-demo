<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="회원가입" />
<%@ include file="../common/head.jspf"%>

<head>
<link rel="stylesheet" href="/resource/css/member.css" type="text/css">
</head>

<section>
  <div class="join-wrap">
    <form method="post" action="../member/doJoin">
      <div class="title">
        회원가입
      </div>
      <ul>
        <li><input type="text" name="loginId" placeholder="아이디" /></li>
        <li><input type="password" name="loginPw"
          placeholder="비밀번호" /></li>
        <li><input type="password" name="loginPwConfirm"
          placeholder="비밀번호 확인" /></li>
        <li><input type="text" name="name" placeholder="이름" /></li>
        <li><input type="text" name="nickname" placeholder="닉네임" /></li>
        <li><input type="text" name="email" placeholder="이메일" /></li>
        <li><input type="text" name="cellphoneNo" placeholder="전화번호" /></li>
        <li><input type="submit" value="회원가입"/></li>
      </ul>
    </form>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>