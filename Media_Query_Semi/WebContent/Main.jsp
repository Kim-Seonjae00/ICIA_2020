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
<script src="https://kit.fontawesome.com/cd5c7a4640.js" crossorigin="anonymous"></script>

</head>
<style>

	.tab_menu{
		text-decoration:none;
		color:white;
		margin-left:10px;
		margin-right:10px;
		font-size:17px;	
	}
	.tab_menu:hover{
		color:gray;	
	}
	
	#slidePoster{
		width:1887px;
		height:758px;
		margin:auto;
		background-image: url("images/slide/교회오빠슬라이드.jpg");
		background-repeat: no-repeat;
    	background-size: cover;
	}
	#bottom {
    height: 150px;
    width: 100%;
    margin: auto;
    text-align: center;
    background: -webkit-linear-gradient(bottom, rgba(29, 29, 31, 1) 0%, rgba(0, 0, 0, 0) 100%);
    padding-top:120px;
}

.posterSel {
    cursor: pointer;
    color: gray;
    padding: 2px;
}

#first {
    color: lightgray;
}
#bannerImg {
	    width: 980px;
	    height: 79px;
	    margin: auto;
}
.line{
    width: 100%;
    height: 0;
    border-top: dashed 2px black;
    margin-bottom: 30px;
}
.category1{
    font-size: 30px;
    margin-bottom: 30px;
    margin-top: 30px;
}
.movie{
    width: 236px;
    padding-top: 8px;
    margin: 4px;
    height: 360px;
    float: left;
    border: solid 2px black;
    color: black;
    font-size: 20px;
    background-color :rgb(245, 246, 247);
}
.movie p{
    font-size: 25px;
}#article{
    width: 1080px;
    margin:auto;
    border: solid 1px rgb(245, 246, 247);
    background-color:rgb(245, 246, 247);
}
.moviePoster{
    width: 100%;
    margin: auto;
    margin-bottom: 15px;
    padding: auto;
    width: 100%;
    height: 385px;
    text-align: center;
    background-color:rgb(245, 246, 247);
}
.poster{
    margin-right: 7px;
    margin-left: 7px;
    background-color:rgb(245, 246, 247);
    width:222px;
    height:346px
}
#movieColumn{
    width: 995px;
    margin :auto;
}
#sideMenu{
    position: absolute;
    left: 1600px;
    top: 874px;
    width: 70px;
    height: 286px;
}
.sideTab{
    height: 70px;
    text-align: center;
    font-size: 20px;
    border: 1px solid white;
    background-color:rgb(95, 0, 128);
}
.sideTab a{
    text-decoration: none;
    color: rgb(0,0,0);
}
</style>
<script>
	var i = 1;
    var posterArr = ["images/slide/교회오빠슬라이드.jpg", "images/slide/사냥의시간슬라이드.jpg", "images/slide/슬럼독밀리어네어.jpg", "images/slide/용길이네곱창집슬라이드.jpg"];
	function selctFn(num, btn) {
		i=num;
        $("#slidePoster").css("background-image", "url(" + posterArr[i] + ")");
        $(".posterSel").css("color", "gray");
        $(btn).css("color", "lightgray");
    }
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
         
	function slideImg() {
        switch (i) {
            case 0:
                $(".posterSel").css("color", "gray");
                $("#first").css("color", "lightgray");
                break;
            case 1:
                $(".posterSel").css("color", "gray");
                $("#second").css("color", "lightgray");
                break;
            case 2:
                $(".posterSel").css("color", "gray");
                $("#third").css("color", "lightgray");
                break;
            case 3:
                $(".posterSel").css("color", "gray");
                $("#fourth").css("color", "lightgray");
                break;
            case 4:
                $(".posterSel").css("color", "gray");
                $("#fifth").css("color", "lightgray");
        }
        $("#slidePoster").css("background-image", "url(" + posterArr[i] + ")");
        i++;
        if (i == 4) {
            i = 0;
        }
        
    }
        setInterval(slideImg, 3000);
		var currentPosition = parseInt($("#sideMenu").css("top"));
	    $(window).scroll(function () {
	        var position = $(window).scrollTop();
	        console.log("position:"+position);
	        if(position<390){
	            $("#sideMenu").stop().animate({ "top": 874 + "px" }, 500);
	            console.log("aaa")
	        }else if(position>1600){
	        	$("#sideMenu").stop().animate({ "top": 2100 + "px" }, 500);
	        	console.log("bbb")
	        }else{
	        	console.log("ddd");
	            $("#sideMenu").stop().animate({ "top": position + currentPosition-400 + "px" }, 300);
	            console.log("머러마ㅣ:"+parseInt($("#sideMenu").css("top")));
	        }
	    });
	});

	function closeBanner() {
    	$("#banner").css("display", "none");
	}
	
</script>

<body>
	<div id="banner">
		<div id="bannerImg"><span id="closeBtn" onclick="closeBanner()">&times;</span></div>
	</div>
	<div id="slidePoster">
		<div id="header" style="background: -webkit-linear-gradient(top, rgba(29, 29, 31, 1) 0%, rgba(0, 0, 0, 0) 100%);">
			<a href="mainForm" style="font-size:65px;font-family: 'Nanum Brush Script'; text-decoration:none; color:white;">청춘극장</a>
			<div id="top_nav" style="border-top:1px solid lightgray;">
				<a href="movieList" class="tab_menu">영화</a>
				<a href="theaterList" class="tab_menu">영화관</a>
				<a href="reservationForm" class="tab_menu">예매</a>
			   	<a href="boardList" class="tab_menu">리뷰</a>
				<c:choose>
			 	  	<c:when test="${sessionScope.mId eq null}">
			        	 <a href="JoinForm.jsp" class="tab_menu">회원가입</a>
			    	     <a href="LoginForm.jsp" class="tab_menu">로그인</a>
				    </c:when>
				      <c:otherwise>
				        	<a href="myPage?mId=${sessionScope.mId}" class="tab_menu">마이페이지</a>
					    <c:if test="${sessionScope.mId eq 'admin'}">
					    	<a href="AdminPage.jsp" class="tab_menu">관리자페이지</a>
			        	</c:if>
			        	<a href="memberLogout" class="tab_menu">로그아웃</a>
			    	</c:otherwise>
			   	</c:choose>
			</div>
		</div>
		<div id="between474" style="height:355px;"></div>
		<div id="bottom">
		<div style="height:90px;"></div>
                <span class="posterSel" id="first" onclick="selctFn(0,'#first')">
                    <i class="fas fa-circle"></i>
                </span>
                <span class="posterSel" id="second" onclick="selctFn(1,'#second')">
                    <i class="fas fa-circle"></i>
                </span>
                <span class="posterSel" id="third" onclick="selctFn(2,'#third')">
                    <i class="fas fa-circle"></i>
                </span>
                <span class="posterSel" id="fourth" onclick="selctFn(3,'#fourth')">
                    <i class="fas fa-circle"></i>
                </span>
            </div>
	</div>
	
	<div id="article">
		<div id="sideMenu">
	        <div class="sideTab" style="padding-top: 10px;"><a href="#titleNowMovie">현재<br>상영작</a></div>
            <div class="sideTab" style="padding-top: 10px;"><a href="#titleSoonMovie">개봉<br>예정작</a></div>
            <div class="sideTab" style="padding-top: 25px;"><a href="#titleEvent">EVENT</a></div>
            <div class="sideTab" style="padding-top: 10px;"><a href="#name"><i class="fas fa-sort-up"></i><br>TOP</a></div>
        </div>
		<div id="movieColumn">
			<h1 class="category1" id="titleNowMovie">현재상영작</h1>
			<div class="line"></div>
			<div id="nowMovie" class="moviePoster">
				<c:forEach var="nowMovie" items="${nowMovieList}" end="3">
					<div class="movie"><img class="poster" src="images/poster/${nowMovie.poster}">
					<p style="margin-top:10px; font-size:20px;">${nowMovie.mTitle}</p>
					</div>
				</c:forEach>
			</div>
		</div>
		<div style="height:30px;"></div>
		<div id="movieColumn" >
			<h1 class="category1" id="titleSoonMovie">상영예정작</h1>
			<div class="line"></div>
			<div id="soonMovie" class="moviePoster">
				<c:forEach var="soonMovie" items="${soonMovieList}" end="3">
					<div class="movie"><img class="poster" src="images/poster/${soonMovie.poster}">
					<p style="margin-top:10px; font-size:20px;">${soonMovie.mTitle}</p>
					</div>
				</c:forEach>
			</div>
		</div>
		<div style="height:30px;"></div>
		<div id="movieColumn" >
			<h1 class="category1" id="titleSoonMovie">예매순위</h1>
			<div class="line"></div>
			<div id="rankMovie" class="moviePoster">
				<c:forEach var="rankMovie" items="${rankMovieList}" end="3">
					<div class="movie"><img class="poster" src="images/poster/${rankMovie.poster}">
					<p style="margin-top:10px; font-size:20px;">${rankMovie.mTitle}</p>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<div style="height:70px;">	
	</div>
	<div id="footer">
        <h1 style="font-size: 20px;">청 춘 극 장</h1>
        서울특별시 송파구 올림픽로 300 롯데월드타워 27층고객센터 1544-8855><br>
        대표이사 원선영 사업자등록번호 313-87-00979통신판매업신고번호 제1184호개인정보 보호책임자 김선재<br>
        COPYRIGHT© LOTTE CINEMA ALL RIGHT RESERVED.
    </div>
</body>
</html>