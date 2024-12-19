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
	
	function idCheck(){
		if("${sessionScope.mId}"==""){
			alert("로그인이 필요한 기능입니다.");
			location.href="LoginForm.jsp";
		}else{
			location.href="getmTitle";
		}
	}
</script>
<style>
	table, th, td{
		text-align: center;
		padding :3px;
	}
	
	#boardList{
		width:1200px;
		margin:auto;
		margin-bottom:30px;
	}
	
	#boardSearch{
		text-align:right;
		width:1200px;
		margin:auto;
		margin-right:380px;
		margin-bottom:30px;
	}
	
	#boardSearch input{
		height:20px;
		width:200px;
		font-size:15px;
	}
	
	#bottom-bar{
		margin:auto;
		width:1200px;
		text-align:center;
	}
	#div10{
		width:1200px;
		height:10px;
	}
	#div10_b{
		width:1200px;
		height:10px;
		border-bottom:1px solid gray;
	}
	
	#div10_t{
		width:1200px;
		height:10px;
		border-top:1px solid gray;
	}
	
	#list tr td{
		border-bottom:1px solid lightgray;
	}
	
	#paging{
		text-align:center;
	}	
	
	a:hover{
		text-decoration:underline;
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
<h1 style="text-align:center;">리뷰 목록</h1>
	<div id="boardSearch">
	<input type="text" name="search" id="search"placeholder="검색">
	<select id="subject">
		<option value="MID">작성자</option>
		<option value="TITLE">제목</option>
	</select>
	<button onclick="searchFn()" style="font-size:15px;">검색</button>
	</div>
	<div id="boardList">
	<div id="div10_t"></div>
		<table>
			<tr>
				<th style="width:80px;">글번호</th>
				<th style="width:100px;">영화</th>
				<th style="width:620px;">제목</th>
				<th style="width:200px">작성자</th>
				<th style="width:200px">작성일자</th>
				<th style="width:100px">조회수</th>
			</tr>	
		</table>
		<div id="div10_b"></div>
		<table id="list">
			<c:forEach var="board" items="${boardList}">
				<tr>
					<td style="width:80px; height:45px;"><strong>${board.bNum}</strong></td>
					<td style="width:100px">${board.mTitle}</td>
					<td style="text-align:left; width:596px; padding-left:30px;"><a style="text-decoration:none; color:black;" href="boardView?bNum=${board.bNum}&page=${paging.page}">${board.boTitle}</a></td>
					<td style="width:200px">${board.mId}</td>
					<td style="width:200px">${board.writeDate}</td>
					<td style="width:100px">${board.bHit}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="paging">
			<!-- 페이징 처리 -->
		<c:if test="${paging.page<=1}">
		[이전]&nbsp;
		</c:if>
		<c:if test="${paging.page>1}">
			<a href="boardList?page=${paging.page-1}">[이전]</a>&nbsp;
		</c:if>
		<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i"
			step="1">
			<c:choose>
				<c:when test="${i eq paging.page}">
				${i}
				</c:when>
				<c:otherwise>
					<a href="boardList?page=${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.page>=paging.maxPage}">
			&nbsp;[다음]
		</c:if>
		<c:if test="${paging.page<paging.maxPage}">
			&nbsp;<a href="boardList?page=${paging.page+1}">[다음]</a>
		</c:if>
	</div>
	<div id="div10"></div>
	<div id="bottom-bar">
	<button onclick="idCheck()">글 쓰기</button>
	<select id="sort">
		<option value="#">정렬</option>
		<option value="hitList">조회수</option>
		<option value="boardList">글 번호</option>
	</select>
	<button onclick="sortFn()">정렬</button>
	</div>
	<div id="footer">
        <h1 style="font-size: 20px;">청 춘 극 장</h1>
        서울특별시 송파구 올림픽로 300 롯데월드타워 27층고객센터 1544-8855><br>
        대표이사 원선영 사업자등록번호 313-87-00979통신판매업신고번호 제1184호개인정보 보호책임자 김선재<br>
        COPYRIGHT© LOTTE CINEMA ALL RIGHT RESERVED.
    </div>
</body>
</html>