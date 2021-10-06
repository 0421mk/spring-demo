<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խù� ����Ʈ</title>
</head>
<body>
	<h1>�Խù� ����Ʈ</h1>
	<table border="1">
		<c:forEach var="article" items="${articles}">
			<thead>
				<tr>
					<th>��ȣ</th>
					<th>�ۼ���¥</th>
					<th>������¥</th>
					<th>�ۼ���</th>
					<th>����</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${article.id}</td>
					<td>${article.regDate.substring(2, 16)}</td>
					<td>${article.updateDate.substring(2, 16)}</td>
					<td>${article.memberId}</td>
					<td><a href="../article/detail?id=${article.id}">${article.title}</a>
					</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
</body>
</html>