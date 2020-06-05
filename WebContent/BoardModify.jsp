<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap" rel="stylesheet">
<link rel="stylesheet" href="Movie.css">
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

	function blankCh(){
		var contents=document.getElementsByClassName("blank_ch");
		var blank_ch=false;
		for(var i=0;i<contents.length;i++){
			if(contents[i].value == ""){
				blank_ch=false;
				break;
			}else{
				blank_ch=true;
			}
		}
		return blank_ch;
	}

	function modifyBoardSubmit(){
		if(blankCh()){
			modifyBoardForm.submit();
		}else{
			alert("빈칸이 존재합니다.");
		}
	}
	function readURL(input) {
		if (input.files && input.files[0]) {
		var reader = new FileReader();
		$('#image_section').css("visibility","visible");
		  
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
	<h2 style="text-align:center">리뷰 작성</h2>
	<div id="main_write">
		<form action="boardModifyProcess?bNum=${board.bNum}" method="post" enctype="multipart/form-data" name="modifyBoardForm">
			<table id="writeBoard_form"style="margin:auto;">
			<tr>
				<td class="category">영화</td>
				<td>
					<select name="mTitle" class="writeRequired blank_ch" style="width:707px;">
						<option value="${board.mTitle}" selected>${board.mTitle}</option>
						<c:forEach var="mTitle" items="${mTitleList}">
							<option value="${mTitle}">${mTitle}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="category">리뷰 제목</td>
				<td>
					<input type="text" name="boTitle" class="writeRequired blank_ch" value="${board.boTitle}">
				</td>
			</tr>
			<tr>
				<td class="category">
					내용
				</td>
				<td style="background-color:white">
					<textarea cols="50" rows="10" name="comments" class="writeRequired2 blank_ch">${board.comments}</textarea>
				</td>
			</tr>
			</table>
			<img id="image_section" src="images/fileUpload/${board.bFiles}" alt="your image" style="width:200px; height:150px;visibility:hidden;">
			<input type="file" name="bFiles" id="imgInput" value="${board.bFiles}"><br>
			
		</form>
		<button onclick="modifyBoardSubmit()">수정</button>
	</div>
</body>
</html>