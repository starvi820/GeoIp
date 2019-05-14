<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

<title>Insert title here</title>

</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<script src="https://malsup.github.com/jquery.form.js"></script>



<body>
		<div class="col-sm-12">
			<div class="row" id="regGoodsImgArea">
				<div class="col-sm-4">
					<label>GeoIP</label>
						<br>
						
						<form id="excelUpForm" enctype="multipart/form-data" method="post" action="/excel/excelUpload">
						
						<input id="excel" name="excel" class="file"
							type="file" multiple data-show-upload="false"
							data-show-caption="true"></input>
							</form> 
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-primary btn-lg"  id="excelUp" onclick="check()">일괄 등록 하기</button>
					
					
					<br>
		
					
		
<!-- Button trigger modal -->
<form method="GET" action="/excel/excelList">
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" onclick="list()"> 개별 등록 하기</button><br><br>
</form>

 <a href= <c:url value="/excel/excelDown"/>>GeoIp 목록 다운로드</a>
  
      </body>
 
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content modal-lg">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
        <h3 class="modal-title" id="myModalLabel">GEOIP 등록하기</h3>
      </div>
      <div class="modal-body">
      
            
      <input id="duplication" type="button" value="Public IP 중복확인"/>
      <input id="ipCheckPublicIp" type="button" value="PublicIp 유효검사"/>
      <input id="ipCheckStartIp" type="button" value="시작 IP 유효검사"/>
      <input id="ipCheckEndIp" type="button" value="끝 IP 유효검사"/>
      <input id="cidrAddressChk" type="button" value= "CIDR 주소 계산"/>
      <input id="cidrInput" type="text" placeholder="ex.10.10.10.10/26"/>
      
      
      <input id="ipRangeCheck" type="button" style="vertical-align:right"  value="사용 가능  Ip 범위 체크"/>
      <a href="${pageContext.request.contextPath}/geoip">지역으로 보기</a>
      
      
      
      
      
      <form id="excelInsert" method="post" action="/excel/excelInsert" role="form">
            
      <input id="isPrivateIp" name="isPrivateIp" type="checkbox" value="true">Private IP 여부<br>
      <input id="publicIp" name="publicIp" type="text" placeholder="Public Ip (ex.58.205.215.0)"><br>
      <input id="startIp" name="startIp" type="text" placeholder="시작 IP(ex 10.10.10.10)"><a></a>
      <input id="endIp" name="endIp" type="text" placeholder="끝 IP(ex. 10.10.10.10)" ><br>
      <input id="countryName" name="countryName" type="text" placeholder="국가명" > <br>
      <input id="cityName" name="cityName" type="text" placeholder="도시명">
     
      
      
       <button type="submit" id="excelInsertAjax" class="btn btn-primary">입력</button>
      </form>
       
       
       
       <table class="table table-bordered table-hover text-center" style="table-layout:fixed;">
       	<tr>
       		<th width="40px" style="text-align: center; vertical-align: middle:">선택</th>     
			<th width="40px" style="text-align: center; vertical-align: middle:">번호</th>       		
       		<th width="100px" style="text-align: center; vertical-align: middle:">시작IP</th>
       		<th width="100px" style="text-align: center; vertical-align: middle:">끝 IP</th>
       		<th width="50px" style="text-align: center; vertical-align: middle:">국가명</th>
       		<th width="70px" style="text-align: center; vertical-align: middle:">도시명</th>
       		<th width="100px" style="text-align: center; vertical-align: middle:">Public Ip</th>
       		<th width="70px" style="text-align: center; vertical-align: middle:">Private Ip 여부</th>
       </tr>
              
       <tbody id="tbody" class="tbody tr:first-child td">
                      
       	<tr>
       	
       		<td><input type="checkbox" name="checkBox"></td>
       		<td style="width:200px; test-align: center; vertical-align: middle;" ></td>
       		<td style="width:200px; test-align: center; vertical-align: middle;" ></td>
       		<td style="width:200px; test-align: center; vertical-align: middle;" ></td>
       		<td style="width:200px; test-align: center; vertical-align: middle;" ></td>
       		<td style="width:200px; test-align: center; vertical-align: middle;" ></td>
       		<td style="width:200px; test-align: center; vertical-align: middle;" ></td>
       		<td style="width:200px; test-align: center; vertical-align: middle;" ></td>
       </tr>
      </tbody>
     </table>
   </div>
      <div class="modal-footer">
      
      		<button type="button" data-toggle="modal" href="#myModal2" id="updateExcel" class="btn btn-primary">수정</button>	
      		<button type="button" data-toggle="modal" id="deleteExcel" class="btn btn-danger">삭제</button>	
         <!--  <a data-toggle="modal" href="#myModal2" id="selectBtn" class="btn btn-primary">수정</a> -->
            </div>
        <button type="button" class="btn btn-primary" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>



<!--  second Modal !!!!!!!!!!!!!! -->


<div class="modal" id="myModal2" aria-hidden="true" style="display: none; z-index: 1060;">
    	<div class="modal-dialog modal-lg">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
              <h4 class="modal-title">GEOIP 변경</h4>
            </div><div class="container"></div>
            <div class="modal-body">
            
                   
            <input id="publicIp1" name="publicIp1" type="text" ><br>
 			<input id="startIp1" name="startIp1" type="text" >
      		<input id="endIp1" name="endIp1" type="text" ><br>
    		<input id="countryName1" name="countryName1" type="text"><br>
      		<input id="cityName1" name="cityName1" type="text"> <br>
      		
      		     		
      		
      		
            
            <div class="col-lg-12" id="ex3_Result1"></div>
            
                                   
            
            </div>
            <div class="modal-footer">
              <a href="#" data-dismiss="modal" class="btn">취소</a>
              <button type="submit" id="updateExcelSubmit" class="btn btn-primary">완료</button><br>
            </div>
          </div>
        </div>
    </div>



	
    
<script src="${pageContext.request.contextPath}/js/excel.js"></script>

</html>