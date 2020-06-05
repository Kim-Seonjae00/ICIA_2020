<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="Movie.css">
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<script>
function blankCh(){
	var contents=document.getElementsByClassName("required");
	var blank_ch=false;
	for(var i=0;i<contents.length;i++){
			console.log("aaaa"+	contents[i].value);
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

function movieSubmit(){
	if(blankCh()){
		movieInsertForm.submit();
	}
}

function readURL(input) {
	if (input.files && input.files[0]) {
	var reader = new FileReader();
	  
	reader.onload = function (e) {
		$('#image_section').attr('src', e.target.result);
	}
	  
	reader.readAsDataURL(input.files[0]);
	}
}
$(document).ready(function () {
	$("#imgInput").change(function(){
   		readURL(this);
	});
});

</script>

</head>
<body>
<h1 style="text-align:center">영화 추가</h1>
<div style="height:30px;"></div>
<div id="form_Join">
	<form action="movieInsert" method="post" name="movieInsertForm" enctype="multipart/form-data">
		<p class="subject">영화제목</p> 
		<div class="inputBox">
			<input type="text" name="mTitle" class="required">
		</div>
		
		<p class="subject">포스터</p> 
		<div style="width:220px; height:290px; border:1px solid lightgray; margin:auto; margin-bottom:5px; padding:2px;">
			<img id="image_section" src="#" alt="포스터 미리보기" style="width:220px; height:290px;">
		</div>
		<div class="inputBox">
			<input type="file" name="poster" id="imgInput" class="required">
		</div>
		
		
		<p class="subject">감독</p>
		<div class="inputBox"> 
			<input type="text" name="director" class="required">
		</div>
		
		<p class="subject">배우</p>
		<div class="inputBox">
			<input type="text" name="actors" class="required">
		</div>
		
		<p class="subject">러닝타임(분)</p>
		<div class="inputBox">
			<input type="number" name="runningTime" class="required">
		</div>
		
		<p class="subject">개봉일</p>
		<div class="inputBox"> 
			<input type="date" name="releaseDate" class="required">
		</div>
		
		<p class="subject">종료일</p>
		<div class="inputBox"> 
			<input type="date" name="endDate" class="required">
		</div>
	</form>
</div>
<div id="buttons">
	<button onclick="movieSubmit()">추가</button>
	<button onclick="location.href='AdminPage.jsp'">취소</button>
</div>
<div id="footer">
        <h1 style="font-size: 20px;">청 춘 극 장</h1>
        서울특별시 송파구 올림픽로 300 롯데월드타워 27층고객센터 1544-8855><br>
        대표이사 원선영 사업자등록번호 313-87-00979통신판매업신고번호 제1184호개인정보 보호책임자 김선재<br>
        COPYRIGHT© LOTTE CINEMA ALL RIGHT RESERVED.
    </div>
</body>
</html>