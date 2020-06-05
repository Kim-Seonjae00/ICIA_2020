
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="Movie.css">
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap" rel="stylesheet">
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
	function movieDetailFn(mTitle){
		location.href="movieDetail?mTitle="+mTitle;
	}
</script>
<style>
.movie_category{
	width:1200px;
	margin:auto;
	text-align:center;
	margin-top:20px;
	height:450px;
}
	
.flip {
  background-color: transparent;
  width: 220px;
  height: 290px;
  perspective: 1000px;
  margin:10px;
  margin-top:20px;
}

.flip-inner {
  position: relative;
  width: 100%;
  height: 100%;
  text-align: center;
  transition: transform 0.6s;
  transform-style: preserve-3d;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
}

.flip:hover .flip-inner {
  transform: rotateY(180deg);
}

.flip-front, .flip-back {
  position: absolute;
  width: 100%;
  height: 100%;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
}

.flip-front {
  background-color: #bbb;
  color: black;
}

.flip-back {
  background-color: #2980b9;
  color: white;
  transform: rotateY(180deg);
}
.flip:hover{
	cursor:pointer;
}
</style>
</head>
<body>
	<div id="banner">
		<div id="bannerImg" style="width:980px; height:100%; margin:auto;"><span id="closeBtn" onclick="closeBanner()">&times;</span></div>
	</div>
	<div id="header" style="background:black">
		<a href="mainForm" style="font-size:65px;font-family: 'Nanum Brush Script'; text-decoration:none; color:white;">청춘극장</a>
	</div>
	<div style="height:10px;"></div>
	<div id="now_Movie" class="movie_category">
		<h1 style="margin-bottom:20px; font-size:25px;">현재 상영작</h1>
		<c:forEach var="nowMovie" items="${nowMovieList}">
		<div class="flip" style="float:left" onclick="movieDetailFn('${nowMovie.mTitle}');">
			<div class="flip-inner">
			<div class="flip-front" style="width:220px;height:290px;">
				<img style="width:220px;height:290px;"src="images/poster/${nowMovie.poster}" class="poster">
			</div>
			<div class="flip-back" style="width:220px;height:290px;">
				<div style="width:220px; height:290px; text-align:100%">
					<h4>감독:${nowMovie.director}</h4>
					<h4>배우:${nowMovie.actors}</h4>
					<h4>개봉일:${fn:substring(nowMovie.releaseDate,0,10)}</h4>
					<h4>평점:<fmt:formatNumber value="${(nowMovie.rate+0.0) div (nowMovie.rHit+0.0)}" pattern=".0"/>(${nowMovie.rHit})</h4>
					<h4>러닝타임:${nowMovie.runningTime}</h4>
				</div>
			</div>
			</div>
				<h3>${nowMovie.mTitle}</h3>
		</div>
		</c:forEach>
	</div>
	<div style="width:1200px; height:0px; margin:auto; border-top:1px gray solid; margin-top:10px; margin-bottom:10px;"></div>
	<div id="soon_Movie" class="movie_category"style="m">
		<h1 style="font-size:25px;">상영 예정작</h1>
		<c:forEach var="soonMovie" items="${soonMovieList}">
			<div class="flip" style="float:left">
				<div class="flip-inner">
					<div class="flip-front" style="width:220px;height:290px;">
						<img style="width:220px;height:290px;"src="images/poster/${soonMovie.poster}" class="poster">
					</div>
					<div class="flip-back" style="width:220px;height:290px;">
						<div style="width:220px; height:290px; text-align:100%">
							<h4>감독:${soonMovie.director}</h4>
							<h4>배우:${soonMovie.actors}</h4>
							<h4>개봉일:${fn:substring(soonMovie.releaseDate,0,10)}</h4>
							<h4>평점:<fmt:formatNumber value="${(soonMovie.rate+0.0) div (soonMovie.rHit+0.0)}" pattern=".0"/>(${soonMovie.rHit})</h4>
							<h4>러닝타임:${soonMovie.runningTime}</h4>
						</div>
					</div>
				</div>
				<h3>${soonMovie.mTitle}</h3>
			</div>
		</c:forEach>
	</div>
	
	<div style="width:1200px; height:0px; margin:auto; border-top:1px gray solid; margin-top:10px; margin-bottom:10px;"></div>
	<div id="rank_Movie" class="movie_category"style="">
		<h1 style="font-size:25px;	">예매 순위</h1>
		<c:forEach var="rankMovie" items="${rankMovieList}">
			<div class="flip" style="float:left">
			<div class="flip-inner">
			<div class="flip-front" style="width:220px;height:290px;">
				<img style="width:220px;height:290px;"src="images/poster/${rankMovie.poster}" class="poster">
			</div>
			<div class="flip-back" style="width:220px;height:290px;">
				<div style="width:220px; height:290px; text-align:100%">
					<h4>감독:${rankMovie.director}</h4>
					<h4>배우:${rankMovie.actors}</h4>
					<h4>개봉일:${fn:substring(rankMovie.releaseDate,0,10)}</h4>
					<h4>평점:<fmt:formatNumber value="${(rankMovie.rate+0.0) div (rankMovie.rHit+0.0)}" pattern=".0"/>(${rankMovie.rHit})</h4>
					<h4>러닝타임:${rankMovie.runningTime}</h4>
				</div>
			</div>
			</div>
				<h3>${rankMovie.mTitle}</h3>
		</div>
		</c:forEach>
	</div>
	
	<div id="footer">
        <h1 style="font-size: 20px;">청 춘 극 장</h1>
        서울특별시 송파구 올림픽로 300 롯데월드타워 27층고객센터 1544-8855><br>
        대표이사 원선영 사업자등록번호 313-87-00979통신판매업신고번호 제1184호개인정보 보호책임자 김선재<br>
        COPYRIGHT© LOTTE CINEMA ALL RIGHT RESERVED.
    </div>
</body>
</html>