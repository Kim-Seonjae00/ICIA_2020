<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<link rel="stylesheet" href="Movie.css">
<link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/cd5c7a4640.js" crossorigin="anonymous"></script>
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
	
	function deleteFn(){
		if(confirm("삭제하시겠습니까?")){
			location.href="boardDelete?bNum=${board.bNum}";
		}else{
			alert("취소되었습니다.");
		}
	}

</script>

<style>
	#board{
		width:800px;
		margin:auto;
	}
	
	#title{
		margin-top:13px;
		margin-bottom:5px;
		padding:3px;
		padding-bottom:8px;
		border-bottom:1px solid gray;
		text-align:center;
	}
	#buttons{
		width:800px;
		margin:auto;
		margin-left:550px;
	}
	#buttons button{
		margin-right:5px;
		width:80px;
		height:25px;
		font-size:13px;
	}
	#line{
		height:0px;
		width:100%;
		margin-top:8px;
		margin-bottom:8px;
		border-bottom:1px solid lightgray;
	}
	table,tr, th,td{
		padding:3px;
	}
	
	table{
		border-collapse:collapse;
	}
</style>

</head>
<body>
<div id="banner">
	<div id="bannerImg" style="width:980px; height:100%; margin:auto;"><span id="closeBtn" onclick="closeBanner()">&times;</span></div>
</div>
<div id="header">
<a href="mainForm" style="font-size:65px;font-family:'Nanum Brush Script'; text-decoration:none; color:white;">청춘극장</a>
</div>
<div style="height:30px;"></div>
<div style="width:650px; margin:auto">
	<div style="width:240px; height:343px; float:left;">
		<img src="images/poster/${movie.poster}">
	</div>
	<div style="float:left; padding:10px; margin-left:20px; padding-top:50px;">
		<p style="font-size:35px; margin-bottom:20px;">${movie.mTitle}</p>
		<p style="font-size:25px;  margin-bottom:8px;"><i class="fas fa-star" style="color:rgb(177,250,10); margin-right:3px;"></i>
		<fmt:formatNumber value="${(movie.rate+0.0) div (movie.rHit+0.0)}" pattern=".0"/>&nbsp;&nbsp;&nbsp;누적관람객 수 ${movie.mHit}명</p>
		<div style="height:0px; width:100%; border-top:1px solid lightgray"></div>		
		<table>	
			<tr>
				<td style="width:90px;">감독</td>
				<td>${movie.director}</td>
			<tr>
				<td style="width:90px;">출연</td>
				<td>${movie.actors}</td>
			</tr>	
			<tr>
				<td>${movie.runningTime}분</td>
				<td>${fn:substring(movie.releaseDate,0,10)} 개봉</td>
			</tr>
			<tr>
				<form action="setStarRate?mTitle=${movie.mTitle}" method="post">
					<td><i class="fas fa-star" style="color:rgb(177,250,10); margin-right:3px;"></i>10/<input type="number" name ="rate" min="0" max="10" value="5" style="width:30px; background-color:rgb(245, 246, 247); font-size:17px;"></td>
					<td><input type="submit" value="평점주기"></td>
				</form>
			</tr>
		</table>
	</div>
</div>
<div id="footer" style="margin-top:800px;">
	<h1 style="font-size: 20px;">청 춘 극 장</h1>
        서울특별시 송파구 올림픽로 300 롯데월드타워 27층고객센터 1544-8855><br>
        대표이사 원선영 사업자등록번호 313-87-00979통신판매업신고번호 제1184호개인정보 보호책임자 김선재<br>
        COPYRIGHT© LOTTE CINEMA ALL RIGHT RESERVED.
</div>
</body>
</html>