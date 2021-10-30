<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="마이페이지" />
<%@ include file="../common/head.jspf"%>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <div class="table-box-type-1">
      <form class="table-box-type-1" method="post" action="../member/doModify">
        <table>
        <colgroup>
          <col width="200" />
        </colgroup>
        <tbody>
          <tr>
            <th>이름</th>
            <th>
              ${rq.loginedMember.name}
            </th>
          </tr>
          <tr>
            <th>별명</th>
            <th>
              <input type="text" name="nickname" placeholder="${rq.loginedMember.nickname}" class="w-96" />
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
              <a href="../member/modifyPw">비밀번호 변경</a>
            </th>
          </tr>
          <tr>
            <th colspan="2">
              <input type="submit" value="회원 정보 수정" class="hover:underline" />
            </th>
          </tr>
        </tbody>
      </table>
      </form>
    </div>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>