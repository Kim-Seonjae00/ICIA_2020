<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="Movie.css">
<script src="https://kit.fontawesome.com/cd5c7a4640.js" crossorigin="anonymous"></script>
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
	function checkFn(id){
		var exp = null;
		var check=false;
		var getCheck = document.getElementById(id);
		if(id=="mId"){
			exp = /^(?=.*[a-z])(?=.*\d)[a-z\d]{6,10}$/;
		}else if(id=="pw"){
			exp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;
		}else if(id=="tel"){
			exp = /^\d{3}-\d{4}-\d{4}$/;
		}
		var check="#"+id+"_check";
		var times="#"+id+"_times";
		
		if(id=="mId"&&getCheck.value.match(exp)){
			$("#overlapBtn").css("visibility", "visible");
		}
		
		if(getCheck.value.match(exp)){
			$(times).css("display","none");
			$(check).css("display","block");
			$(check).css("color","green");
			check=true;
		}else{
			$(check).css("display","none");
			$(times).css("display","block");
			$(times).css("color","red");
			check=false;
		}
		return check;
	}
	function allCheck(){
		var check = false;
		var list=["mId","pw","tel"];
		for(var i=0;i<list.length;i++){
			if(checkFn(list[i])){
				check=true;
			}else{
				location.href="#"+list[i];
				check=false;
				alert("회원가입 형식에 일치하지 않습니다.");
				break;
			}
		}
		return check;
	}
	function blankCh(){
		var contents=document.getElementsByClassName("required");
		var blank_ch=false;
		for(var i=0;i<contents.length;i++){
			if(contents[i].value == ""){
				blank_ch=false;
				alert("빈칸이 존재합니다.");
				break;
			}else{
				blank_ch=true;
			}
		}
		return blank_ch;
	}
	function equalsPw(){
		var pw=document.getElementById("pw").value;
		var cPw=document.getElementById("cPw").value;
		var equals=false;
		if(pw==cPw){
			$("#cPw_times").css("display","none");
			$("#cPw_check").css("display","block");
			$("#cPw_check").css("color","green");
			equals=true;
		}else{
			$("#cPw_check").css("display","none");
			$("#cPw_times").css("display","block");
			$("#cPw_times").css("color","red");
			alert("비밀번호가 일치하지 않습니다.");
			equals=false;
		}
		return equals;
	}
	function submitModify(){
		if(equalsPw() && blankCh() && allCheck()){	
			modifyForm.submit();
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
					<td><a href="boardHistroy?mId=${sessionScope.mId}">내가 쓴 리뷰보기</a></td> 
					<td style="text-align: right;">${bCount}개</td>
				</tr>
				<tr>
					<td><a href="ticketHistroy?mId=${sessionScope.mId}">관람한 영화</a></td>
					<td style="text-align: right;">${tCount}개</td>
				</tr>
			</table>
		</div>
	</div>
	<div id="top_nav_myPage">	
		<div class="myPageBox">
			<h2>내정보 관리</h2>
			<p class="mIcon"><i class="fas fa-user-cog"></i></p>
			<a>회원 정보 수정</a><br>
			<a href="memberDelete?mId=${sessionScope.mId}">회원 탈퇴</a><br>
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
	<div id="main_modify">
		<h2>회원정보 수정</h2>
		<form action="memberModifyProcess" method="post" name="modifyForm">
			<p class="subject"><i class="fas fa-user"></i> 아이디</p>
			<table>
			<tr>
				<td>
					<div class="inputBox" style="margin-bottom:0px; margin-left:284px;">
						<input type="text" id="mId" class="required" name="mId" value="${member.mId}" readonly">				
					</div>
				</td>
				<td>
				</td>
			</tr>
			</table>
			
			<p class="subject"><i class="fas fa-key"></i> 비밀번호</p>
			<div class="inputBox">
				<input type="password" id="pw" class="required checkInput" name="pw" placeholder="영대소문자, 숫자, 특수문자 포함  8자~16자" onblur="checkFn('pw')">
				<div id="pw_check" class="check" style="display:none"><i class="fas fa-check"></i></div>
				<div id="pw_times" class="check" style="display:none"><i class="fas fa-times"></i></div>
			</div>
			
			<p class="subject"><i class="fas fa-lock"></i> 비밀번호 확인</p>
			<div class="inputBox">
				<input type="password" id="cPw" class="required checkInput" onblur="equalsPw()">
				<div id="cPw_check" class="check" style="display:none"><i class="fas fa-check"></i></div>
				<div id="cPw_times" class="check" style="display:none"><i class="fas fa-times"></i></div>
			</div>
			
			<p class="subject"><i class="fas fa-signature"></i> 이름</p>
			<div class="inputBox">
				<input type="text" name="mName" class="required" value="${member.mName}">
			</div>
			
			<p class="subject"><i class="fas fa-mobile-alt"></i> 전화번호</p>
			<div class="inputBox">
				<input type="text" id="tel" class="required checkInput" name="tel" value="${member.tel}" onblur="checkFn('tel')">
				<div id="tel_check" class="check" style="display:none"><i class="fas fa-check"></i></div>
				<div id="tel_times" class="check" style="display:none"><i class="fas fa-times"></i></div>
			</div>
			
			<p class="subject"><i class="fas fa-map-marker-alt"></i> 주소</p>
			<div class="addressBox">
				<input type="text" id="sample4_postcode" class="required" name="post" value="${addressList[0]}">
				<input type="button" id="addressBtn" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="sample4_roadAddress" class="required" name="road" value="${addressList[1]}">
				<input type="text" id="sample4_jibunAddress" class="required" name = "jibun" value="${addressList[2]}">
				<span id="guide" style="color:#999;display:none"></span>
				<input type="text" id="sample4_detailAddress" class="required" name="detail" class="input" value="${addressList[3]}">
				<input type="text" id="sample4_extraAddress" class="required" name="extra" value="${addressList[4]}"><br>
			</div>
			
			<p class="subject"><i class="fas fa-envelope"></i> 이메일</p>
			<div class="inputBox">
				<input type="text" name="email" class="required" value="${member.email}">
			</div>
		</form>
	</div>
	<div id="buttons">
		<button id="joinBtn" onclick="submitModify()">정보수정</button>
	</div>
	<div id="footer">
        <h1 style="font-size: 20px;">청 춘 극 장</h1>
        서울특별시 송파구 올림픽로 300 롯데월드타워 27층고객센터 1544-8855><br>
        대표이사 원선영 사업자등록번호 313-87-00979통신판매업신고번호 제1184호개인정보 보호책임자 김선재<br>
        COPYRIGHT© LOTTE CINEMA ALL RIGHT RESERVED.
    </div>
</body>
</html>