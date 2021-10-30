<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="로그인" />
<%@ include file="../common/head.jspf"%>

<head>
<link rel="stylesheet" href="/resource/css/member.css" type="text/css">
</head>

<section>
  <div class="login-wrap">
    <form method="post" action="../member/doLogin">
      <div class="title">
        로그인
      </div>
      <ul>
        <li><input type="text" name="loginId" placeholder="로그인 아이디" /></li>
        <li><input type="password" name="loginPw"
          placeholder="로그인 비밀번호" /></li>
        <li><input type="submit" value="로그인"/></li>
      </ul>
    </form>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>