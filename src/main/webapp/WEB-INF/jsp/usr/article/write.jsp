<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="게시물 작성" />
<%@ include file="../common/head.jspf"%>

<head>
<link rel="stylesheet"
  href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<link rel="stylesheet" href="/resource/css/article.css" type="text/css">
</head>

<section>
  <!-- <button onclick="seeHtml()">html보기</button> -->
  <div class="article-write-wrap">
    <form method="POST" action="../article/doWrite"
      onsubmit="return validateForm()" name="writeForm">
      <input type="hidden" name="id" value="${article.id}" />
      <input type="hidden" name="body" value='' />
      <ul>
        <li><select name="boardId" id="category">
            <option disabled selected value="0">카테고리</option>
            <option value="1">공지사항</option>
            <option value="2">자유게시판</option>
        </select></li>
        <li><input type="text" name="title"
          placeholder="제목을 입력해주세요." /></li>
        <li><div id="editor"></div></li>
        <!-- <textarea rows="10" name="body"></textarea> -->
        <li><input type="submit" value="글 쓰기" /></li>
      </ul>
    </form>
  </div>
</section>

<script
  src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>

<script>
	const Editor = toastui.Editor;

	const editor = new Editor({
		el : document.querySelector('#editor'),
		height : '500px',
		initialEditType : 'wysiwyg',
		previewStyle : 'vertical'
	});
	
	seeHtml = function() {
		alert(editor.getHTML());
	}
	
	function validateForm() {
		var x = document.getElementById("category").value;

		if (x == 0) {
			alert("카테고리를 선택해주세요.");
			return false;
		}
		
		document.forms['writeForm']['body'].value = editor.getHTML();
		
	}
</script>

<%@ include file="../common/foot.jspf"%>