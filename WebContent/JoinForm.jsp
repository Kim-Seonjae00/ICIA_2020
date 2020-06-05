<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/cd5c7a4640.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="Movie.css">
<script>
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}

function submitJoin(){
	if(idOverlapCh()&& equalsPw() && blankCh() && allCheck()){	
		joinForm.submit();
	}
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

function getOverlapPopup(){
	var id=document.getElementById("mId").value;
	window.open("idOverlap?id="+id,"아이디 중복체크","width=400, height=300, left=100, top=50");
}

function idOverlapCh(){
	var result = document.getElementById("overlapCh").value;
	var overlap = false;

	if(result=="true"){
		overlap = true;
	}else if(result=="false"){
		 alert("중복되는 아이디는 사용이 불가능 합니다.");
		overlap = false;
	}else{
		alert("중복확인은 필수 사항입니다.");
		overlap = false;
	}
	return overlap;
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
});
function closeBanner(){
	$("#banner").css("display","none");
}
</script>

<style>
	
</style>


</head>
<body>
	<div id="banner">
		<div id="bannerImg" style="width:980px; height:100%; margin:auto;"><span id="closeBtn" onclick="closeBanner()">&times;</span></div>
	</div>
	<div id="header">
		<a href="mainForm" style="font-size:65px;font-family: 'Nanum Brush Script'; text-decoration:none; color:white;">청춘극장</a>
	</div>
	<div style="height:30px;"></div>
	<div id="form_Join">
		<h1>회원가입</h1>
		<form action="memberJoin" method="post" name="joinForm">
			<p class="subject"><i class="fas fa-user"></i> 아이디</p>
			<table>
			<tr>
				<td>
					<div class="inputBox" style="margin-bottom:0px; margin-left:284px;">
						<input type="text" id="mId" class="required checkInput" name="mId" placeholder="영소문자, 숫자 포함 6자~10자" onblur="checkFn('mId')">				
						<input type="text" id="overlapCh" readonly style="display:none">
						<div id="mId_check" class="check" style="display:none"><i class="fas fa-check"></i></div>
						<div id="mId_times" class="check" style="display:none"><i class="fas fa-times"></i></div>
					</div>
				</td>
				<td>
					<div id="overlapBtn" style="visibility:hidden;"><input type="button" onclick="getOverlapPopup()" value="중복확인"></div>
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
				<input type="text" name="mName" class="required" placeholder="이름">
			</div>
			
			<p class="subject"><i class="fas fa-birthday-cake"></i> 생년월일</p>
			<div class="inputBox">
				<input type="date" name="birth" class="required">
			</div>
			
			<p class="subject"><i class="fas fa-mobile-alt"></i> 전화번호</p>
			<div class="inputBox">
				<input type="text" id="tel" class="required checkInput" name="tel" placeholder="010-0000-0000" onblur="checkFn('tel')">
				<div id="tel_check" class="check" style="display:none"><i class="fas fa-check"></i></div>
				<div id="tel_times" class="check" style="display:none"><i class="fas fa-times"></i></div>
			</div>
			
			<p class="subject"><i class="fas fa-map-marker-alt"></i> 주소</p>
			<div class="addressBox">
				<input type="text" id="sample4_postcode" class="required" name="post"placeholder="우편번호">
				<input type="button" id="addressBtn" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="sample4_roadAddress" class="required" name="road" placeholder="도로명주소">
				<input type="text" id="sample4_jibunAddress" class="required" name = "jibun" placeholder="지번주소">
				<span id="guide" style="color:#999;display:none"></span>
				<input type="text" id="sample4_detailAddress" class="required" name="detail" class="input" placeholder="상세주소">
				<input type="text" id="sample4_extraAddress" class="required" name="extra" placeholder="참고항목"><br>
			</div>
			
			<p class="subject"><i class="fas fa-envelope"></i> 이메일</p>
			<div class="inputBox">
				<input type="text" name="email" class="required">
			</div>
		</form>
	</div>
	<div id="buttons">
		<button id="joinBtn" onclick="submitJoin()">회원가입</button>
	</div>
	 <div id="footer">
        <h1 style="font-size: 20px;">청 춘 극 장</h1>
        서울특별시 송파구 올림픽로 300 롯데월드타워 27층고객센터 1544-8855><br>
        대표이사 원선영 사업자등록번호 313-87-00979통신판매업신고번호 제1184호개인정보 보호책임자 김선재<br>
        COPYRIGHT© LOTTE CINEMA ALL RIGHT RESERVED.
    </div>
</body>
</html>