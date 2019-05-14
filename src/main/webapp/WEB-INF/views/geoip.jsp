<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">

<title>Insert title here</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
	
</script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<script src="https://api.mqcdn.com/sdk/mapquest-js/v1.3.2/mapquest.js"></script>
<link type="text/css" rel="stylesheet"
	href="https://api.mqcdn.com/sdk/mapquest-js/v1.3.2/mapquest.css" />


<body>
	<div class="col-md-4">
		<label for="t1_os" class="col-form-label">대륙</label> <select
			class="form-control" id="continent" name="continent"
			onchange="continent(this)">
			<option value="">== 선택 ==</option>
			<c:forEach var="continent" items="${continent}">
				<option value="${continent.continentCode}" id="conCode">${continent.continentName}</option>
			</c:forEach>

		</select>

	</div>
	<div class="col-md-4" id="countryVal">
		<label for="t1_status" class="col-form-label">국가</label> <select
			class="form-control" id="country" onchange="country(this)">


		</select>
	</div>
	<div class="col-md-4" id="cityVal">
		<label for="to_status" class="col-form-label">도시</label> <select
			class="form-control" id="city" onchange="city(this)">
			<option value=""></option>
		</select>
	</div>

	<div class="col-md-4" id="locVal">
		<label for="to_status" class="col-form-label">지역</label> <select
			class="form-control" id="loc" onchange="loc(this)">
			<option value=""></option>
		</select>
	</div>
	<br>

	<!--  지도 -->
	<div id="map" style="width: 100%; height: 450px;"></div>
	<br>


	<button type="submit" id="button" class="btn btn-primary"
		onClick="List(this)">도시조회</button>
	<button type="submit" id="locCode" class="btn btn-primary"
		onClick="locCode(this)">지역조회</button>
	<button type="submit" id="button" class="btn btn-primary"
		onClick="ListAll()">전체조회</button>




	<div id="longitude"></div>
	<div id="latitude"></div>

	<br>
	<br>
	<button type="button" class="btn btn-primary" data-toggle="modal"
		data-target="#myModal">입력</button>
	<button type="submit" id="updateGeoip" class="btn btn-primary"
		data-toggle="modal" data-target="#updateModal">수정</button>
	<button type="submit" id="deleteGeoip" class="btn btn-danger">삭제</button>
	<a href=<c:url value="/excel/excelDown"/>>GeoIp 목록 다운로드</a>



	<table class="table table-bordered table-hover text-center"
		style="table-layout: fixed;">
		<tr>
			<th width="40px" style="text-align: center; vertical-align: middle:">선택</th>
			<th width="40px" style="text-align: center; vertical-align: middle:">번호</th>
			<th width="100px" style="text-align: center; vertical-align: middle:">시작IP</th>
			<th width="100px" style="text-align: center; vertical-align: middle:">끝
				IP</th>
			<th width="50px" style="text-align: center; vertical-align: middle:">국가명</th>
			<th width="70px" style="text-align: center; vertical-align: middle:">도시명</th>
			<th width="70px" style="text-align: center; vertical-align: middle:">지역명</th>
			<th width="100px" style="text-align: center; vertical-align: middle:">Public
				Ip</th>
			<th width="70px" style="text-align: center; vertical-align: middle:">Private
				Ip 여부</th>
			<th width="70px" style="text-align: center; vertical-align: middle:">고객사</th>
		</tr>

		<tbody id="tbody2" class="tbody tr:first-child td">


			<tr>
				<td><input type="checkbox" id="checkGeoip" name="checkGeoip"></td>
				<td
					style="width: 200px; test-align: center; vertical-align: middle;"></td>
				<td
					style="width: 200px; test-align: center; vertical-align: middle;"></td>
				<td
					style="width: 200px; test-align: center; vertical-align: middle;"></td>
				<td
					style="width: 200px; test-align: center; vertical-align: middle;"></td>
				<td
					style="width: 200px; test-align: center; vertical-align: middle;"></td>
				<td
					style="width: 200px; test-align: center; vertical-align: middle;"></td>
				<td
					style="width: 200px; test-align: center; vertical-align: middle;"></td>
				<td
					style="width: 200px; test-align: center; vertical-align: middle;"></td>
				<td
					style="width: 200px; test-align: center; vertical-align: middle;"></td>
			</tr>
		</tbody>
	</table>


</body>



<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content modal-lg">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<h3 class="modal-title" id="myModalLabel">GEOIP 등록하기</h3>
			</div>
			<div class="modal-body">


				<input id="duplication" type="button" value="Public IP 중복확인" /> <input
					id="ipCheckPublicIp" type="button" value="PublicIp 유효검사" /> <input
					id="ipCheckStartIp" type="button" value="시작 IP 유효검사" /> <input
					id="ipCheckEndIp" type="button" value="끝 IP 유효검사" /> <input
					id="cidrAddressChk" type="button" value="CIDR 주소 계산" /> <input
					id="cidrInput" type="text" placeholder="ex.10.10.10.10/26" /> <input
					id="ipRangeCheck" type="button" style="vertical-align: right"
					value="사용 가능  Ip 범위 체크" />




				<form id="excelInsert" method="post" action="/excel/excelInsert"
					role="form">

					<input id="isPrivateIp" name="isPrivateIp" type="checkbox"
						value="true">Private IP 여부<br> <input id="publicIp"
						name="publicIp" type="text"
						placeholder="Public Ip (ex.58.205.215.0)"><br> <input
						id="startIp" name="startIp" type="text"
						placeholder="시작 IP(ex 10.10.10.10)"> <input id="endIp"
						name="endIp" type="text" placeholder="끝 IP(ex. 10.10.10.10)"><br>
					<input id="countryName" name="countryName" type="text"
						placeholder="국가명"> <br> <input id="cityName"
						name="cityName" type="text" placeholder="도시명"><br> <input
						id="cityLocationName" name="cityLocationName" type="text"
						placeholder="지역명"><br> <input id="companyName"
						name="companyName" type="text" placeholder="고객사 명">



					<button type="submit" id="geoipInsertAjax" class="btn btn-primary">입력</button>
				</form>


			</div>
			<div class="modal-footer">

				<!--  <a data-toggle="modal" href="#myModal2" id="selectBtn" class="btn btn-primary">수정</a> -->
			</div>
			<button type="button" class="btn btn-primary" data-dismiss="modal">취소</button>
		</div>
	</div>
</div>




<!-- UPDATE MODAL 2 -->

<!-- Modal -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content modal-lg">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<h3 class="modal-title" id="myModalLabel">GEOIP 수정</h3>
			</div>
			<div class="modal-body" id="updateBody">


				<input id="duplication2" type="button" value="Public IP 중복확인" /> <input
					id="ipCheckPublicIp2" type="button" value="PublicIp 유효검사" /> <input
					id="ipCheckStartIp2" type="button" value="시작 IP 유효검사" /> <input
					id="ipCheckEndIp2" type="button" value="끝 IP 유효검사" /> <input
					id="cidrAddressChk2" type="button" value="CIDR 주소 계산" /> <input
					id="cidrInput2" type="text" placeholder="ex.10.10.10.10/26" /> <input
					id="ipRangeCheck2" type="button" style="vertical-align: right"
					value="사용 가능  Ip 범위 체크" /><br> <input id="publicIp2"
					name="publicIp2" type="text"><br> <input id="startIp2"
					name="startIp2" type="text"> <input id="endIp2"
					name="endIp2" type="text"><br> <input
					id="countryName2" name="countryName2" type="text"><br>
				<input id="cityName2" name="cityName2" type="text"><br>
				<input id="cityLocationName2" name="cityLocationName2" type="text"><br>
				<input id="companyName2" name="companyName2" type="text">








				<button type="submit" id="updateSubmit" class="btn btn-primary">완료</button>
				<br>


			</div>
			<div class="modal-footer">

				<!--  <a data-toggle="modal" href="#myModal2" id="selectBtn" class="btn btn-primary">수정</a> -->
			</div>
			<button type="button" class="btn btn-primary" data-dismiss="modal">취소</button>
		</div>
	</div>
</div>


<nav>
	<ul class="pagination">
		<li><a href="#" aria-label="Previous"> <span
				aria-hidden="true">&laquo;</span>
		</a></li>
		<li><a href="#">1</a></li>
		<li><a href="#">2</a></li>
		<li><a href="#">3</a></li>
		<li><a href="#">4</a></li>
		<li><a href="#">5</a></li>
		<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
</nav>



<!--  페이징 처리 

<ul class="pagenation">
<c:if test="${!geoipPage.first }">
<li class="previous">
<a href ="?page=${geoipPage.number-1 }">&larr; newer Posts</a>
</li>
</c:if>
<c:if test="${!geoipPage.last }">
<li class="next">
<a href="?page=${geoipPage.number+1 }">Older Posts &rarr;</a>
</li>
</c:if>

</ul>


-->






<script src="${pageContext.request.contextPath}/js/excel.js"></script>
<script src="${pageContext.request.contextPath}/js/test.js"></script>

</html>