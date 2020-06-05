<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="Movie.css">
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap" rel="stylesheet">

<style>
	#main_reservation_first, #main_reservation_second{
		width:1200px;
		height:600px;
		border:1px solid black;
		margin:auto;
		margin-bottom:100px;
	} 
	
	#main_reservation_first div, #main_reservation_second div{
		border:1px solid black;
		margin:auto;
	} 
	
	.areaList, .tLocationList{
		cursor:pointer;
		border:1px solid black;
		margin:0px;
		padding:8px;
		width:182px;
	}
	
	.mTitleList{
		cursor:pointer;
		border:1px solid black;
		margin:0px;
		padding:8px;
	}
	.scheduleList{
		cursor:pointer;
		border:1px solid black;
		margin:0px;
		padding:8px;
	}
	.section{
		width:400px;
		height:100%;
		float:left;
	}
	ul{
		list-style:none;
		padding-left:0px;
		margin:0px;
		text-align:center;
   }
   .category{
   		width:100%;
   		text-align:center;
   }
   .section_theater{
   		width:198px;
   		float:left;
   }
   .tLocationList, .mTitleList, .scheduleList{
		display:none;
   }
   #seatSelect{
   		width:70%;
   		height:570px;
   		float:left;
   		text-align:center;
   		vertical-align:middle;
   		padding-top:30px;
   }
   #seatSelect button{
   	width:30px;
   	height:30px;
   	margin:3px;
   }
   #screen{
   	width:80%;
   	margin:auto;
   	height:25px;
   	border:1px solid black;
   }
	
</style>
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
	
	function setMovieFn(movie){
		document.getElementById("mTitle").value=movie;
		
		document.getElementById("house").value="";
		document.getElementById("startTime").value="";
		document.getElementById("endTime").value="";
		
		$(".scheduleList").css("display","");
		$("."+$.escapeSelector(movie.replace(/ /gi,""))).css("display","block");
	}
	function setAreaFn(area){
		document.getElementById("area").value=area;
		
		document.getElementById("mTitle").value="";
		document.getElementById("tLocation").value="";
		document.getElementById("house").value="";
		document.getElementById("startTime").value="";
		document.getElementById("endTime").value="";
		
		$(".tLocationList").css("display","");
		$(".mTitleList").css("display","");
		$(".scheduleList").css("display","");
		$("."+$.escapeSelector(area)).css("display","block");
	}
	function settLocationFn(tLocation){
		document.getElementById("tLocation").value=tLocation;
		document.getElementById("mTitle").value="";
		document.getElementById("house").value="";
		document.getElementById("startTime").value="";
		document.getElementById("endTime").value="";
		
		$(".mTitleList").css("display","");
		$(".scheduleList").css("display","");
		$("."+$.escapeSelector(tLocation)).css("display","block");
	}
	
	function setSchedule(house, startTime, endTime){
		document.getElementById("house").value=house;
		document.getElementById("startTime").value=startTime;
		document.getElementById("endTime").value=endTime;
	}
	
	function setSeat(){
		var house = document.getElementById("house").value;
		var tLocatoin = document.getElementById("tLocation").value;
		var startTime = document.getElementById("startTime").value.replace(" ","");
		
		var width=document.getElementById(tLocation.value+house+"_width").value;
		var height=document.getElementById(tLocation.value+house+"_height").value;
		var seatSelect = document.getElementById("seatSelect");
		var selectedSeat = "";
		
		var seatList = document.getElementsByClassName(tLocation.value+house+startTime);
		
		for(var i=0;i<seatList.length;i++){
			selectedSeat += seatList[i].value;
		}
		selectedSeat = selectedSeat.split(" ");
		seatSelect.innerHTML="<div id='screen'>SCREEN</div><div style='width:100%; height:80px; border:none;'>";
		for(var i=0;i<height;i++){
			seatSelect.innerHTML += String.fromCharCode(i+65);
			for(var j=1;j<=width;j++){
				if(selectedSeat.includes(String.fromCharCode(i+65)+j)){
					seatSelect.innerHTML += "<button id='"+String.fromCharCode(i+65)+j+"' style='background-color:gray; border:none' readonly>"+j+"</button>";
				}
				else{
					seatSelect.innerHTML += "<button id='"+String.fromCharCode(i+65)+j+"' class='seatBtn' value='"+String.fromCharCode(i+65)+j+"'onclick='choiceFn(this)'>"+j+"</button>";
				}
			}
			seatSelect.innerHTML+="<br>";
		}
		if(startTime != ""){
			$("#main_reservation_first").css("display","none");
			$("#main_reservation_second").css("display","block");			
		}
	}
	
	function choiceFn(seat){
		var youth = document.getElementById("youthSelect").value;
		var adult = document.getElementById("adultSelect").value;
		var seatCount=0;
		var totalPerson=parseInt(youth)+parseInt(adult);
		if(totalPerson==0){
			alert("인원을 선택하세요.");
		}else{
			var seatBtn = document.getElementsByClassName("seatBtn");
			for(var i=0;i<seatBtn.length;i++){
				if(seatBtn[i].style.backgroundColor=="rgb(196, 0, 0)"){
					seatCount++;
				}
			}
			if(seat.style.color==""){
					if(seatCount==totalPerson){
					alert("인원초과");
				}else{
			    	$(seat).css("background-color","rgb(196,0,0)");
			        $(seat).css("color","white");
				}
		    }else{
		    	$(seat).css("background-color","");
		        $(seat).css("color",""); 
		    }
		}
	}
	
	function setPeople(){
		var youth = document.getElementById("youthSelect").value;
		var adult = document.getElementById("adultSelect").value;
		
		document.getElementById("youth").value=youth;
		document.getElementById("adult").value=adult;
		document.getElementById("totalPriceSelect").value = (youth*7000)+(adult*9000);
		document.getElementById("totalPrice").value = (youth*7000)+(adult*9000);
	}
	
	function ticketSubmit(){
		var seatBtn = document.getElementsByClassName("seatBtn");
		var youth = document.getElementById("youthSelect").value;
		var adult = document.getElementById("adultSelect").value;
		var totalPerson=parseInt(youth)+parseInt(adult);
		var seatCount = 0;
		document.getElementById("seat").value="";
		for(var i=0;i<seatBtn.length;i++){
			if(seatBtn[i].style.backgroundColor=="rgb(196, 0, 0)"){
				seatCount++;
			}
		}
		
		if(seatCount!=0){
			if(seatCount==totalPerson){
				for(var i=0;i<seatBtn.length;i++){
					if(seatBtn[i].style.backgroundColor=="rgb(196, 0, 0)"){
						document.getElementById("seat").value += " "+seatBtn[i].value;	
						if(blankCh()){
							ticketReservationForm.submit();
						}
					}
				}
			}else{
				alert("인원 수를 확인하세요.");
			}
		}else{
			alert("인원을 선택해 주새요");
		}
	}
	
	function back(){
		$("#main_reservation_first").css("display","block");
		$("#main_reservation_second").css("display","none");	
	}
	
</script>
</head>


<body>
	<div id="banner">
		<div id="bannerImg" style="width:980px; height:100%; margin:auto;"><span id="closeBtn" onclick="closeBanner()">&times;</span></div>
	</div>
	<div id="header" style="background-color:black; margin-bottom:30px;">
		<a href="mainForm" style="font-size:65px;font-family: 'Nanum Brush Script'; text-decoration:none; color:white;">청춘극장</a>
	</div>
	<div id="main_reservation_first">
		<div id="theaterList" class="section">
			<div class="category">영화관</div>
			<div id="sectcion_area" class="section_theater">
				<ul>
					<li class="areaList" onclick="setAreaFn('서울')">서울</li>
					<li class="areaList" onclick="setAreaFn('인천')">인천</li>
					<li class="areaList" onclick="setAreaFn('충청/대전')">충청/대전</li>		
				</ul>
			</div>	
			<div id="section_tLocation" class="section_theater">
				<ul>
					<c:forEach var="theater" items="${theaterList}"  varStatus="status">
						<li class="tLocationList ${theater.area}" onclick="settLocationFn('${theater.tLocation}')">${theater.tLocation}</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		
		<div id="movieList" class="section"> 
			<div class="category">영화</div>
			<ul>
				<c:forEach var="schedule" items="${scheduleJoinList}">
					<li class="mTitleList ${schedule.tLocation}" onclick="setMovieFn('${schedule.mTitle}')">${schedule.mTitle}</li>
				</c:forEach>
			</ul>
		
		</div>
		<div id="scheduleList" class="section" style="width:394px !important;">
		<div class="category">상영 시간표</div>
		<ul>
			<c:forEach var="schedule" items="${scheduleJoinList}">
				<li class="scheduleList ${fn:replace(schedule.mTitle,' ','')}" onclick="setSchedule('${schedule.house}','${schedule.startTime}','${schedule.endTime}')">
					${schedule.house}<br>
					<c:out value="${schedule.mTitle}"/><br>
					${schedule.startTime} ~ ${schedule.endTime}<br>	
					잔여좌석:(${schedule.totalSeat})<br>
				</li>
			</c:forEach>
		</ul>
		</div>
		<button onclick="setSeat()">좌석선택</button>
	</div>
	
	<div id="main_reservation_second" style="display:none">
		<div id="seatSelect">
			
		</div>
		<div id="selectPeople">
			유아/청소년 : 7000원
			성인 : 9000원<br>
			유아/청소년(최대 9명) : <input type="number" id="youthSelect" value="0" min="0" max="9" onchange="setPeople()"><br>
			성인(최대 9명) : <input type="number" id="adultSelect" value="0" min="0" max="9" onchange="setPeople()"><br>
			총 결제금액: <input type="number" id="totalPriceSelect" value="0" readonly>
		</div>
		<button onclick="ticketSubmit()">예매</button>
	</div>
	<button onclick="back()">이전</button>
	<button onclick="location.href='mainForm'">취소</button>		
	
	<div style="display:none">
	<c:forEach var="house" items="${houseList}">
		<input type="number" id="${house.tLocation}${house.house}_width" value="${house.width}"><br>
		<input type="number" id="${house.tLocation}${house.house}_height" value="${house.height}"><br>
	</c:forEach>
	</div>
	
	<div style="display:none">
		<c:forEach var="ticket" items="${ticketList}">
			<input type="text" class="${ticket.tLocation}${ticket.house}${fn:replace(ticket.startTime,' ','')}" value="${ticket.seat}">
		</c:forEach>
	</div>
	
	<form action="ticketReservation" name="ticketReservationForm" style="display:none">
		<input type="text" name="mId" value="${sessionScope.mId}"><br>
		<input type="text" name="mTitle" id="mTitle" class="required required_reservation_first">
		<input type="text" name="area" id="area" class="required required_reservation_first">
		<input type="text" name="tLocation" id="tLocation" class="required required_reservation_first">
		<input type="text" name="house" id="house" class="required required_reservation_first">
		<input type="text" name="startTime" id="startTime" class="required required_reservation_first">
		<input type="text" name="endTime" id="endTime" class="required required_reservation_first">
		<input type="number" name="youth" id="youth" class="required">
		<input type="number" name="adult" id="adult" class="required">
		<input type="number" name="totalPrice" id="totalPrice" class="required">
		<input type="text" name="seat" id="seat" class="required">
	</form>
	<div id="footer">
        <h1 style="font-size: 20px;">청 춘 극 장</h1>
        서울특별시 송파구 올림픽로 300 롯데월드타워 27층고객센터 1544-8855><br>
        대표이사 원선영 사업자등록번호 313-87-00979통신판매업신고번호 제1184호개인정보 보호책임자 김선재<br>
        COPYRIGHT© LOTTE CINEMA ALL RIGHT RESERVED.
    </div>
</body>
</html>