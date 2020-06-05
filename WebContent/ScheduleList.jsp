<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table tr,th,td{
		border:1px solid black;
		padding:3px;
		text-align:center;
	}
	table{
		border-collapse:collapse;
	}
</style>
<script>
	function scheduleDel(tLocation, house, startTime){
		if(confirm("삭제하시겠습니까?")){
			location.href="scheduleDelete?tLocation="+tLocation+"&house="+house+"&startTime="+startTime;
		}else{
			console.log("아니오");
		}
	}
</script>
</head>
<body>
	<h2>영화 스케줄</h2>
	<table>
		<tr>
			<th>영화관</th>
			<th>상영관</th>
			<th>영화제목</th>
			<th>상영시간</th>
			<th>종료시간</th>
			<th>잔여좌석</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="schedule" items="${scheduleList}">
			<tr>
				<td>${schedule.tLocation}</td>
				<td>${schedule.house}</td>
				<td>${schedule.mTitle}</td>
				<td>${schedule.startTime}</td>
				<td>${schedule.endTime}</td>
				<td>${schedule.totalSeat}</td>
				<td><button onclick="scheduleDel('${schedule.tLocation}','${schedule.house}','${schedule.startTime}')">삭제</button></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>