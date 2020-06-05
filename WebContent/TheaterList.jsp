<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="Movie.css">
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap" rel="stylesheet">

</head>
<script>
$(document).ready(function(){
	 var bannerNum = Math.floor(Math.random() * 4 + 1);
    switch (bannerNum) {
        case 1:
            backgroundColor = "rgb(6,13,21)";
            $("#closeBtn").css("color", "white");
            break;
        case 2:
            backgroundColor = "rgb(4,14,23)";
            $("#closeBtn").css("color", "white");
            break;
        case 3:
            backgroundColor = "rgb(9,10,12)";
            $("#closeBtn").css("color", "white");
            break;
        case 4:
            backgroundColor = "rgb(199,235,251)";
            break;
    }
    var banner = "url('images/banner1/배너" + bannerNum + ".PNG')";
    $("#bannerImg").css("background-image", banner)
    $("#banner").css("background-color", backgroundColor);
});

	function closeBanner() {
    	$("#banner").css("display", "none");
	}
</script>
<style>
#theaterList_Main{
	width:980px;
	margin:auto;
	text-align:center;
}

.theater{
	color:black;
	margin:5px;
}
</style>


<body>
	<div id="banner">
		<div id="bannerImg" style="width:980px; height:100%; margin:auto;"><span id="closeBtn" onclick="closeBanner()">&times;</span></div>
	</div>
		<div id="header" style="background-color:black">
			<a href="mainForm" style="font-size:65px;font-family: 'Nanum Brush Script'; text-decoration:none; color:white;">청춘극장</a>
		</div>
	<div id="theaterList_Main">
		<h1>영화관</h1>
		<h2>서울</h2>
		<c:forEach var="theater" items="${theaterList}"> 
			<c:if test="${theater.area=='서울'}">
				<a href="ticketReservation?tLocation=${theater.tLocation}" class="theater">${theater.tLocation}</a>
			</c:if>
		</c:forEach>
		<div style="height:10px; border-top:1px solid gray; margin-top:20px;"></div>
		<h2>인천</h2>
		<c:forEach var="theater" items="${theaterList}"> 
			<c:if test="${theater.area=='인천'}">
				<a href="ticketReservation?tLocation=${theater.tLocation}" class="theater">${theater.tLocation}</a>
			</c:if>
		</c:forEach>
		<div style="height:10px; border-top:1px solid gray; margin-top:20px;"></div>
		<h2>충청/대전</h2>
		<c:forEach var="theater" items="${theaterList}"> 
			<c:if test="${theater.area=='충청/대전'}">
				<a href="ticketReservation?tLocation=${theater.tLocation}" class="theater">${theater.tLocation}</a>
			</c:if>
		</c:forEach>
	</div>
	
	<div id="loginAd" style="width:980px; height:180px; margin:auto; margin-top:50px;background-image:url('images/login/광고.jpg')"></div>
	
	<div id="footer">
        <h1 style="font-size: 20px;">청 춘 극 장</h1>
        서울특별시 송파구 올림픽로 300 롯데월드타워 27층고객센터 1544-8855><br>
        대표이사 원선영 사업자등록번호 313-87-00979통신판매업신고번호 제1184호개인정보 보호책임자 김선재<br>
        COPYRIGHT© LOTTE CINEMA ALL RIGHT RESERVED.
    </div>
</body>
</html>