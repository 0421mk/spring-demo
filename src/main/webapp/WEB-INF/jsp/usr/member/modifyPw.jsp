<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="비밀번호 변경" />
<%@ include file="../common/head.jspf"%>

<head>
<link rel="stylesheet" href="/resource/css/member.css" type="text/css">
</head>

<section>
  <div class="modifyPw-wrap">
    <form method="post" action="../member/doModifyPw">
      <div class="title">
        비밀번호 변경
      </div>
      <ul>
        <li><input type="password" name="loginPw"
          placeholder="새 비밀번호" /></li>
          <li><input type="password" name="loginPwConfirm"
          placeholder="새 비밀번호 확인" /></li>
        <li><input type="submit" value="비밀번호 변경"/></li>
      </ul>
    </form>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>