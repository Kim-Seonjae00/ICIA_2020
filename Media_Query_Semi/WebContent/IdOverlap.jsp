<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
	window.onload = function setParents(){
		opener.document.getElementById("overlapCh").value = ${overlap};
	}
</script>

</head>
<body>
	<c:choose>
		<c:when test="${overlap == true}">
			<h2 style="color:blue">사용가능한 아이디입니다.</h2>
		</c:when>
		<c:otherwise>
			<h2 style="color:red">사용 불가능한 아이디입니다.</h2>
		</c:otherwise>
	</c:choose>
	<button onclick="window.close()">창닫기</button>
</body>
</html>