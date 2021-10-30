<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="마이페이지" />
<%@ include file="../common/head.jspf"%>

<head>
<link rel="stylesheet" href="/resource/css/member.css" type="text/css">
</head>

<section>
  <div class="checkPw-wrap">
    <form method="post" action="../member/doCheckPassword">
      <div class="title">
        비밀번호 확인
      </div>
      <ul>
        <li><input type="password" name="loginPw"
          placeholder="비밀번호를 입력해주세요." /></li>
        <li><input type="submit" value="비밀번호 확인"/></li>
      </ul>
    </form>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>