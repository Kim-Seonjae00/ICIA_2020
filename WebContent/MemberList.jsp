<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap" rel="stylesheet">
<style>
	table,tr,td,th{
		border:1px solid black;
		padding:5px;
		border-collapse:collapse;
		text-align:center;
	}
	body{
		text-align:center;
	}
</style>

</head>
<body>
<h2>회원 리스트</h2>
	<table style="margin:auto">
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>생년월일</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>이메일</th>
			<th>멤버십등급</th>
			<th>상세조회</th>
			<th>회원삭제</th>
		</tr>
		<c:forEach var="member" items="${memberList}">
			<c:if test="${member.mId ne 'admin'}">
				<tr>
					<td>${member.mId}</td>
					<td>${member.pw}</td>
					<td>${member.mName}</td>
					<td>${member.birth}</td>
					<td>${member.tel}</td>
					<td>${member.address}</td>
					<td>${member.email}</td>
					<td>${member.membership }</td>
					<td><a href="memberDetail?mId=${member.mId}">조회</a></td>
					<td><a href = "#" onclick="return delChk('${member.mId}');">삭제</a></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<button onclick="location.href='AdminPage.jsp'" style="margin-top:5px;">관리자 페이지</button>
</body>
</html>