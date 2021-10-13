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
            <th><button class="btn btn-xs btn-primary" value="1">좋아요</button></th>
            <td id="like-value">${article.extra_likePoint}</td>
          </tr>
          <tr class="likeWrap">
            <th><button class="btn btn-xs btn-danger" value="-1">싫어요</button></th>
            <td id="dislike-value">${article.extra_disLikePoint * -1}</td>
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



    <section class="mt-5">
      <div class="container mx-auto px-3">
        <h1>Comment</h1>
        <c:if test="${rq.logined}">
          <form class="table-box-type-1" method="POST"
            action="../reply/doWrite">
            <input type="hidden" name="articleId" value="${article.id}" />
            <table>
              <colgroup>
                <col width="200" />
              </colgroup>
              <tbody>
                <tr>
                  <th>작성자</th>
                  <td>${rq.loginedMember.nickname}</td>
                </tr>
                <tr>
                  <th>내용</th>
                  <td><textarea rows="10" name="body"
                      class="w-full"></textarea></td>
                </tr>
                <tr>
                  <th colspan="2"><input type="submit"
                    value="댓글 작성" class="hover:underline" /></th>
                </tr>
              </tbody>
            </table>
          </form>
        </c:if>
        <c:if test="${!rq.logined}">
          <div>
            로그인 후 이용해주세요.
            <a href="/user/member/login">로그인 바로가기</a>
          </div>
        </c:if>
      </div>
    </section>
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
				var jsonResult = JSON.parse(result);

				if (jsonResult.type == 0) {
					alert("로그인 후 이용해주세요.");
					return false;
				} else if (jsonResult.type == 1) {
					alert("이미 추천하였습니다.");
					return false;
				} else if (jsonResult.type == 2) {
					alert("이미 비추천하였습니다.");
					return false;
				} else if (jsonResult.type == 3) {
					$('#like-value').text(jsonResult.likePoint);
					$('#dislike-value').text(jsonResult.disLikePoint*-1);
					return false;
				} else if (jsonResult.type == 4){
					$('#like-value').text(jsonResult.likePoint);
					$('#dislike-value').text(jsonResult.disLikePoint*-1);
					return false;
				}
			},
			error : function(jqXHR, status, error) {
				alert("알수 없는 에러 [ " + error + " ]");
			}
		});
	});
</script>

<%@ include file="../common/foot.jspf"%>