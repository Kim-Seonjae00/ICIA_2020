<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	
	function submitLogin(){
		var idList=["mId","pw"];
		$(".blank").css("display","none");
		var blankCh = true;
		for(var i=0;i<idList.length;i++){
			if(document.getElementById(idList[i]).value==""){
				var blank="#"+idList[i]+"_blank";
				location.href="#"+idList[i];
				$(blank).css("display","block");
				blankCh=false;
				break;
			}
		}
		if(blankCh){
			loginForm.submit();
		}
	}
</script>

</head>
<body>
	<div id="banner">
		<div id="bannerImg" style="width:980px; height:100%; margin:auto;"><span id="closeBtn" onclick="closeBanner()">&times;</span></div>
	</div>
	<div id="header">
		<a href="mainForm" style="font-size:65px;font-family: 'Nanum Brush Script'; text-decoration:none; color:white;">청춘극장</a>
	</div>
	<div style="height:30px;"></div>
	<div id="form_Login">
	<h1 style="font-size:25px;">로그인</h1>
	<form action="memberLogin" method="post" name="loginForm">
		<div class="inputBox" style="margin-bottom:10px;">
			<input type="text" class="required" id="mId" name="mId" placeholder="아이디">
		</div>
		<p id="mId_blank" class="blank" style="display:none; visibility:visible;">아이디를 입력해주세요.</p>
		<div class="inputBox" style="margin-top:10px; margin-bottom:10px;">
			<input type="password" id="pw" class="required" name="pw" placeholder="비밀번호">
		</div>
		<p id="pw_blank" class="blank" style="display:none; visibility:visible;">비밀번호를 입력해주세요.</p>
	</form>
	<button id="loginBtn" onclick="submitLogin()" style="margin-top:10px; font-size:17px;">로그인</button><br>
	<div style="height:30px; width:430px; margin:auto; border-bottom:1px solid lightgray;"></div>
	<div style="width:400px; margin:auto; text-align:center; padding-top:10px;">
		<a href="#" style="color:gray; margin-right:20px;">아이디 찾기</a>
		<a href="#" style="color:gray; padding-right:10px;">비밀번호  찾기</a>
		<a href="JoinForm.jsp" style="color:gray; margin-left:10px;">회원가입</a>
	</div>
	</div>
	<div id="loginAd" style="width:980px; height:180px; margin:auto; margin-top:50px;background-image:url('images/login/로그인1.jpg')"></div>
	<div id="footer">
        <h1 style="font-size: 20px;">청 춘 극 장</h1>
        서울특별시 송파구 올림픽로 300 롯데월드타워 27층고객센터 1544-8855><br>
        대표이사 원선영 사업자등록번호 313-87-00979통신판매업신고번호 제1184호개인정보 보호책임자 김선재<br>
        COPYRIGHT© LOTTE CINEMA ALL RIGHT RESERVED.
    </div>
</body>
</html>