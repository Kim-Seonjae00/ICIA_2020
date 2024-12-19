<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Movie.css">
<title>Insert title here</title>
<script>
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

function theaterSubmit(){
	if(blankCh()){
		theaterInsertForm.submit();
	}
}
</script>
</head>
<body>
	<h2>영화관 추가</h2>
	<div id="form_Join">
	<form action="theaterInsert" method="post" name="theaterInsertForm">
		<p class="subject">지역</p> 
		<div class="inputBox">
			<input type="text" name="area" class="required">
		</div>
		
		<p class="subject">영화관 이름</p> 
		<div class="inputBox">
			<input type="text" name="tLocation" class="required">
		</div>
	</form>
</div>
	<div id="buttons">
		<button onclick="theaterSubmit()">추가</button>
		<button onclick="location.href='AdminPage.jsp'">취소</button>
	</div>
</body>
</html>