<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<link rel="stylesheet" href="Movie.css">
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
</style>

</head>
<body>
<div id="banner">
		<div id="bannerImg" style="width:980px; height:100%; margin:auto;"><span id="closeBtn" onclick="closeBanner()">&times;</span></div>
</div>
<div id="header">
	<a href="mainForm" style="font-size:65px;font-family: 'Nanum Brush Script'; text-decoration:none; color:white;">청춘극장</a>
</div>
<div id="board">
		<div id="title">
			<span style="font-size:25px;"><strong>[${board.mTitle}] ${board.boTitle}</strong></span>
		</div>
		<div style="border-bottom:1px solid gray;">
		<span style="float:right; color:gray;">${board.writeDate}</span>
		<strong>작성자 :${board.mId}</strong>
		<div id="line"></div>
		${board.comments}
		<br>
		<c:if test="${board.bFiles ne null}">
			<img src="images/fileUpload/${board.bFiles}" style="width:240px; height:300px;">
		</c:if>
		<p style="color:gray; text-align:right;">조회수 : ${board.bHit}&nbsp;</p>
		</div>
	</div>
	<br>
	<div id="buttons">
		<div id="button1" style="width:360px; float:left">
		<button onclick="location.href='boardList?page=${page}'">글 목록</button>
		<c:if test="${board.mId == sessionScope.mId || sessionScope.mId=='admin'}">
			<button onclick="deleteFn()">글 삭제</button>
		</c:if>
		<c:if test="${board.mId == sessionScope.mId}">
			<button onclick="location.href='boardModify?bNum=${board.bNum}'">글 수정</button>
		</c:if>
		</div>
	</div>
	<div id="footer">
        <h1 style="font-size: 20px;">청 춘 극 장</h1>
        서울특별시 송파구 올림픽로 300 롯데월드타워 27층고객센터 1544-8855><br>
        대표이사 원선영 사업자등록번호 313-87-00979통신판매업신고번호 제1184호개인정보 보호책임자 김선재<br>
        COPYRIGHT© LOTTE CINEMA ALL RIGHT RESERVED.
    </div>
</body>
</html>