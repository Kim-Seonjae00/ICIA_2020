<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Movie.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function blankCh(){
	var contents=document.getElementsByClassName("required");
	var blank_ch=false;
	for(var i=0;i<contents.length;i++){
		if(contents[i].value == ""){
			alert("빈칸이 존재합니다.");
			blank_ch=false;
			break;
		}else{
			blank_ch=true;
		}
	}
	return blank_ch;
}

function seatCh(){
	var width=document.getElementById("width").value;
	var height=document.getElementById("height").value;
	var seat=document.getElementById("seat").value;
	var seat_ch=false;
	
	if(width*height<seat){
		alert("가로*세로가 총 좌석 수보다 작습니다.");
	}else{
		seat_ch=true;
	}
	return seat_ch;
}

function houseSubmit(){
	if(blankCh() && seatCh()){
		houseInsertForm.submit();
	}
}


</script>
</head>
<body>
	<div id="form_Join">
	<h2>상영관 추가</h2>
	<form action="houseInsert" name="houseInsertForm">
		<p class="subject">영화관</p>
		<div class="inputBox">
			<select name="tLocation" class="required">
				<option value="">영화관</option>
					<optgroup label="인천">
						<c:forEach var="theater" items="${theaterList}">
							<c:if test="${theater.area=='인천'}">
								<option value="${theater.tLocation}">${theater.tLocation}</option>
							</c:if>
						</c:forEach>
					</optgroup>
											
					<optgroup label="서울">
						<c:forEach var="theater" items="${theaterList}">
							<c:if test="${theater.area=='서울'}">
								<option value="${theater.tLocation}">${theater.tLocation}</option>
							</c:if>	
						</c:forEach>
					</optgroup>
					
					<optgroup label="충청/대전">
						<c:forEach var="theater" items="${theaterList}">
							<c:if test="${theater.area=='충청/대전'}">
								<option value="${theater.tLocation}">${theater.tLocation}</option>
							</c:if>
						</c:forEach>
					</optgroup>
			</select><br>
		</div>
		<p class="subject">상영관 이름</p>
		<div class="inputBox"> 
			<input type="text" name="house" class="required">
		</div>
		
		<p class="subject">총 좌석 수</p>
		<div class="inputBox">
			<input type="number" id="seat" name="totalSeat" class="required">
		</div>
		
		<p class="subject">좌석 배치</p>
		가로
		<div class="inputBox"> 
			<input type="number" id="width" name="width" class="required">
		</div>
		
		세로
		<div class="inputBox"> 
			<input type="number" id="height" name="height" class="required">
		</div>
	</form>
	<div id="buttons">
		<button onclick="houseSubmit()">추가</button>
		<button onclick="location.href='AdminPage.jsp'">취소</button>
	</div>
	</div>
</body>
</html>