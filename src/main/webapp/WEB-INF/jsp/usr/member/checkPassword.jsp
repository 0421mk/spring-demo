<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="마이페이지" />
<%@ include file="../common/head.jspf"%>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <div class="table-box-type-1">
      <form class="table-box-type-1" method="post" action="../member/doCheckPassword">
      <table>
        <colgroup>
          <col width="200" />
        </colgroup>
        <tbody>
          <tr>
            <th>비밀번호를 입력해주세요.</th>
            <th>
              <input type="password" name="loginPw" placeholder="로그인 비밀번호" class="w-96" />
            </th>
          </tr>
          <tr>
            <th colspan="2">
              <input type="submit" value="비밀번호 확인" class="hover:underline" />
            </th>
          </tr>
        </tbody>
      </table>
    </form>
    </div>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>