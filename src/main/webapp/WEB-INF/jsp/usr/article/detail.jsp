<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="게시물 상세" />
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
            <th>번호</th>
            <td>${article.id}</td>
          </tr>
          <tr>
            <th>조회수</th>
            <td class="article_detail_count">${article.hitCount}</td>
          </tr>
          <tr>
          <tr class="likeWrap">
            <th><button class="btn btn-xs btn-primary"
                id="like-btn" value="1">좋아요</button></th>
            <td>${article.extra_likePoint}</td>
          </tr>
          <tr class="likeWrap">
            <th><button class="btn btn-xs btn-danger"
                id="dislike-btn" value="-1">싫어요</button></th>
            <td>${article.extra_disLikePoint * -1}</td>
          </tr>
          <tr>
            <th>작성날짜</th>
            <td>${article.getForPrintRegDate()}</td>
          </tr>
          <tr>
            <th>수정날짜</th>
            <td>${article.getForPrintUpdateDate()}</td>
          </tr>
          <tr>
            <th>작성자</th>
            <td>${article.extra_writerName}</td>
          </tr>
          <tr>
            <th>제목</th>
            <td>${article.title}</td>
          </tr>
          <tr>
            <th>내용</th>
            <td>${article.body}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="btns mt-5">
      <button class="hover:underline" type="button"
        onclick="history.back();">뒤로가기</button>
      <a class="btn-text-link" href="../article/modify?id=${article.id}">게시물
        수정</a> <a class="btn-text-link"
        onclick="if (confirm('정말 삭제하시겠습니까?') == false) return false;"
        href="../article/doDelete?id=${article.id}">게시물 삭제</a>
    </div>
  </div>
</section>

<script>
	$('.likeWrap button').click(function() {
		var val = $(this).val();

		var likeData = {
			value : val,
			articleId : ${article.id}
		};

		$.ajax({
			type : "POST",
			url : "../article/doLike",
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify(likeData),
			success : function(result) {
				if (result == 0) {
					alert("로그인 후 이용해주세요.");
					return false;
				} else if (result == 1) {
					alert("이미 추천하였습니다.");
					return false;
				} else if (result == 2) {
					alert("이미 비추천하였습니다.");
					return false;
				} else if (result == 3) {
					alert("수정 완료");
					return false;
				} else if (result == 4){
					<!-- if likeVal == 1 이라면 likVal 값 수정 -->
					alert("성공");
				}
			},
			error : function(jqXHR, status, error) {
				alert("알수 없는 에러 [ " + error + " ]");
			}
		});
	});
</script>

<%@ include file="../common/foot.jspf"%>