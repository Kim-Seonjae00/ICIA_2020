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

function scheduleSubmit(){
	if(blankCh()){
		scheduleInsertForm.submit();
	}
}

function setHouseFn(){
	var theaterSelect = document.getElementById("theaterSelection");
    var selectValue = theaterSelect.options[theaterSelect.selectedIndex].value;    
	var houseList = document.getElementsByClassName(selectValue+"house");
	var select = document.getElementById("houseOption");
	select.innerHTML="<option selected>선택</option>";
	
    for(var i=0;i<houseList.length;i++){	
    	select.innerHTML +="<option value="+houseList[i].value+">"+houseList[i].value+"</option>";
    }
}

function setTotalSeat(){
	console.log("aa");
	var theaterSelect = document.getElementById("theaterSelection");
    var tSelectValue = theaterSelect.options[theaterSelect.selectedIndex].value;
    
    var houseSelect = document.getElementById("houseOption");
    var hSelectValue = houseSelect.options[houseSelect.selectedIndex].value;
    
    var totalSeatList = document.getElementsByClassName(tSelectValue+hSelectValue);
    
    for(var i=0;i<totalSeatList.length;i++){
    	document.getElementById("totalSeat").value=totalSeatList[i].value;
    }
}

function setEndTime(){
	var inputTime = document.getElementById("startTime").value;
	var startTime = new Date(inputTime);
	
	var movieSelect = document.getElementById("mTitle");
    var selectValue = movieSelect.options[movieSelect.selectedIndex].value;    
    var addTime = parseInt(document.getElementById(selectValue).value)+10;
    
	startTime.setMinutes(startTime.getMinutes()+addTime);
	startTime = String(startTime);
	endTime = inputTime.replace(inputTime.substring(11),startTime.substring(16,21))
	document.getElementById("endTime").value=endTime;
	console.log(endTime);
}

function setMin(){
	var today = new Date().toISOString().slice(0,16);
	document.getElementById("startTime").setAttribute("min",today);
}

</script>
</head>
<body>
	<div id="form_Join">
	<h2>스케줄 추가</h2>
	<form action="scheduleInsert" name="scheduleInsertForm" method="post">
		<p class="subject">영화관</p>
		<div class="inputBox">
			<select name="tLocation" 	id="theaterSelection" class="required" onchange="setHouseFn()">
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
			</select>
			
			<c:forEach var="theaterJoin" items="${theaterJoinList}">
				<input type="text" class="${theaterJoin.tLocation}house" value="${theaterJoin.house}" style="display:none">
				<input type="text" class="${theaterJoin.tLocation}${theaterJoin.house}" value="${theaterJoin.totalSeat}" style="display:none">
			</c:forEach>
		</div>
		<p class="subject">상영관</p>
		<div class="inputBox" id="houseSelection">
			<select name="house" id="houseOption" class="required" onchange="setTotalSeat()">
			</select>
		</div>
		
		<p class="subject">전체 좌석</p>
		<div class="inputBox">
			<input type="number" name="totalSeat" id="totalSeat" value="" class="required" readonly>
		</div>
		
		<p class="subject">영화</p>
		<div class="inputBox"> 
			<select name="mTitle" id="mTitle" class="required">
				<option value="">영화제목</option>
				<c:forEach var="movie" items="${movieList}">
					<option value="${movie.mTitle}">${movie.mTitle}</option>
				</c:forEach>
			</select>
				<c:forEach var="movie" items="${movieList}">
					<input id="${movie.mTitle}" type="number" value="${movie.runningTime}" style="display:none;">
				</c:forEach>
		</div>
		
		<p class="subject">시작 시간</p>
		<div class="inputBox">
			<input type="datetime-local" id="startTime" name="startTime" class="required" onclick="setMin()" onblur="setEndTime()">
		</div>
		
		<p class="subject">종료시간</p>
		<div class="inputBox"> 
			<input type="datetime-local" id="endTime" name="endTime" class="required" readonly>
		</div>
		
	</form>
	<div id="buttons">
		<button onclick="scheduleSubmit()">추가</button>
		<button onclick="location.href='AdminPage.jsp'">취소</button>
	</div>
	</div>
</body>
</html>