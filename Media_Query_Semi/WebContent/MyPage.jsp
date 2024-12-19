<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="Movie.css">
<script src="https://kit.fontawesome.com/cd5c7a4640.js" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>

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
		a{
			color:black;
		}
	</style>
</head>
<body>
	<div id="banner">
		<div id="bannerImg" style="width:980px; height:100%; margin:auto;"><span id="closeBtn" onclick="closeBanner()">&times;</span></div>
	</div>
	<div id="header">
		<a href="mainForm" style="font-size:65px;font-family: 'Nanum Brush Script'; text-decoration:none; color:white;">청춘극장</a>
	</div>
	<div style="height:50px;">
	</div>
	<div id="side_nav_myPage">
		<div id="loginBox">
			<a style="font-size:18px; padding-left:5px;">${sessionScope.mId}</a>
			<a href="memberLogout" style="float:right; padding-right:5px;">로그아웃</a>
			<table>
				<tr>
					<td>멤버십 등급</td>
					<td style="text-align: right;">${membership}</td>
				</tr>
				<tr>
					<td><a href="boardHistory?mId=${sessionScope.mId}">내가 쓴 리뷰보기</a></td> 
					<td style="text-align: right;">${bCount}개</td>
				</tr>
				<tr>
					<td><a href="ticketHistory?mId=${sessionScope.mId}">전체 예매내역</a></td>
					<td style="text-align: right;">${tCount}건</td>
				</tr>
			</table>
		</div>
	</div>
	<div id="top_nav_myPage">	
		<div class="myPageBox">
			<h2>내정보 관리</h2>
			<p class="mIcon"><i class="fas fa-user-cog"></i></p>
			<a href="memberModify?mId=${sessionScope.mId}">회원 정보 수정</a><br>
			<a href="memberDelete?mId=${sessionScope.mId}">회원탈퇴</a><br>
		</div>
		<div class="myPageBox center">
			<h2>내리뷰 관리</h2>
			<p class="mIcon"><i class="far fa-list-alt"></i></p>
			<a href="boardHistory?mId=${sessionScope.mId}">내가 쓴 리뷰 보기</a><br>
			<a href="WriteBoard.jsp">리뷰 쓰러 가기</a>
		</div>
		<div class="myPageBox">
			<h2>내티켓 관리</h2>
			<p class="mIcon"><i class="fas fa-ticket-alt"></i></p>
			<a>전체 예매기록 보기</a>	
			<br>
			<a>현재 예매내역 보기</a>
		</div>
	</div>
	<div style="height:210px; width:100%;"></div>
	<div id="main_myPage">
		<div id="loginAd" style="width:980px; height:180px; margin:auto; margin-top:50px;background-image:url('images/login/로그인1.jpg')"></div>
	</div>
	<div id="footer">
        <h1 style="font-size: 20px;">청 춘 극 장</h1>
        서울특별시 송파구 올림픽로 300 롯데월드타워 27층고객센터 1544-8855><br>
        대표이사 원선영 사업자등록번호 313-87-00979통신판매업신고번호 제1184호개인정보 보호책임자 김선재<br>
        COPYRIGHT© LOTTE CINEMA ALL RIGHT RESERVED.
    </div>
</body>
</html>