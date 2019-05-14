	$(document).ready(function() {
	});

	function checkFileType(filePath) {
		var fileFormat = filePath.split(".");
		if (fileFormat.indexOf("xls") > -1) {
			return true;
		} else if (fileFormat.indexOf("xlsx") > -1) {
			return true;
		} else {
			return false;
		}
	}

	function check() {
		var file = $("#excel").val();
		if (file == "" || file == null) {
			alert("파일을 선택");
			return false;
		} else if (!checkFileType(file)) {
			alert("엑셀 파일만 업로드");
			return false;
		}
				
		var fileFormat = file.split(".");
		var fileType = fileFormat[1];
		if (confirm("업로드 하시겠습니까?")) {
			
			$("#excelUpForm").attr("action", "/excel/excelUpload");
			var options = {
				
				success : function(data) {
					$("#ajax-content").html(data);
					location.href='/';
				},
				
				enctype:"multipart/form-data",
				type : "POST",
				data : {
					"excelType" : fileType
				}
			};
			
			$("form").ajaxSubmit(options);
		}
	}


$(document).ready(function(){
    $("#myBtn").click(function(){
    	 $("#myModal").modal('list');
    		
    	      
    });
});


var list=function(){
 $.ajax({
	type:'GET',
	url:'/excel/excelList',
	data:"json",
	success:function(data){
		
		$("#tbody").html('');
	
		
				for(var i=0; i<data.length; i++){
					
var test =	
		   "<tr><td><input type='checkbox' name='checkBox'>"+''+"</td>"+
		   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ data[i].ipIndex +"</td>" +
		   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ data[i].startIp +"</td>" +
		   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ data[i].endIp +"</td>" +
		   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ data[i].countryName +"</td>"+
		   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ data[i].cityName +"</td>"+
		   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ data[i].publicIp +"</td>"+
		   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ data[i].isPrivateIp +"</td>"
		   "</tr>"
		   
		   $("#tbody").append(test);
					
				}
			},
			
	error:function(){
		alert("리스트 호출 실패");
	}
});
 
};

$(document).ready(function(){
	
	$("#excelInsertAjax").click(function(){
		
				
		$.ajax({
			type :'POST',
			url : '/excel/excelInsert' ,
			headers : {
				"Content-Type" : "application/json;charset=UTF-8",
				"X-HTTP-Method-Override" : "POST"
			},
			
			data : JSON.stringify({
				"startIp" : $('#startIp').val(),
				"endIp" : $('#endIp').val(),
				"countryName" : $('#countryName').val(),
				"cityName" : $('#cityName').val(),
				"cityLocationName" : $('#cityLocationName').val(),
				"isPrivateIp" : $("input:checkbox[id='isPrivateIp']").prop('checked'),
				"publicIp" : $('#publicIp').val(),
			}),
					
						
					
			success : function(data){
								
				alert('등록 되었습니다.');
				location.href='/';
			},
			
			error : function(request,status,error){
				alert('등록 실패');
				location.href='/';
				console.log(error);
				console.log(request);
				console.log(status);
							
			}
					
		})
	})
});

$(document).ready(function(){
	$("#duplication").click(function(){
		
		
		if($("#publicIp").val()){
		
		var query = {publicIp:$("#publicIp").val()};
		
		$.ajax({
			url:"/excel/duplication",
			type:"POST",
			data : query,
			success : function(data){
				
				if(data==1){
					alert("Public IP가 중복되었습니다.");
					$("#publicIp").val("");
					
				}else if (data==-1){
					alert("사용가능한 Public IP 입니다.");
					
				}
			}
		});
	}else{
		alert("사용할 Public IP 입력하세요");
		$("#publicIp").focus();
	}
		
	});
		
});



$(document).ready(function(){
	$("#ipCheckPublicIp").click(function(){
		
		
		if($("#publicIp").val()){
		
		var query = {publicIp:$("#publicIp").val()};
		
		$.ajax({
			url:"/excel/publicIpChk",
			type:"POST",
			data : query,
			success : function(data){
				if(data==-1){
					alert("유효하지 않은 IP 입니다.");
					$("#publicIp").val("");
					
				}else if (data==1){
					alert("사용가능한 Public IP 입니다.");
					
				}
			}
		});
	}else{
		alert("사용할 Public IP 입력하세요");
		$("#publicIp").focus();
	}
		
	});
		
});

$(document).ready(function(){
	$("#ipCheckStartIp").click(function(){
		
		
		if($("#startIp").val()){
		
		var query = {startIp:$("#startIp").val()};
		
		$.ajax({
			url:"/excel/startIpChk",
			type:"POST",
			data : query,
			success : function(data){
				
				if(data==-1){
					alert("유효하지 않은 시작 IP 입니다.");
					$("#startIp").val("");
					
				}else if (data==1){
					alert("사용가능한 시작 IP 입니다.");
					
				}
			}
		});
	}else{
		alert("사용할 시작 IP 입력하세요");
		$("#startIp").focus();
	}
		
	});
		
});




$(document).ready(function(){
	$("#ipCheckEndIp").click(function(){
		
		
		if($("#endIp").val()){
		
		var query = {endIp:$("#endIp").val()};
		
		$.ajax({
			url:"/excel/endIpChk",
			type:"POST",
			data : query,
			success : function(data){
				
				if(data==-1){
					alert("유효하지 않은 끝 IP 입니다.");
					$("#endIp").val("");
					
				}else if (data==1){
					alert("사용가능한 끝 IP 입니다.");
					
				}
			}
		});
	}else{
		alert("사용할 끝 IP 입력하세요");
		$("#endIp").focus();
	}
		
	});
		
});





$(document).ready(function(){
	$("#cidrAddressChk").click(function(){
		
		
		if($("#cidrInput").val()){
		
		var query = {cidrInput:$("#cidrInput").val()};
		
		$.ajax({
			url:"/excel/cidrChk",
			type:"POST",
			data : query,
			success : function(data){
				
				alert('넷 마스크 :'+data.resultNetMask+"\n"+
						'네트워크 IP :' +data.resultNetworkAddress+"\n"+
						'브로드캐스트 : '+data.resultBroadcastAddress+"\n"+
						'시작 IP :'+data.resultStartIp+"\n"+
						'끝 IP :'+data.resultEndIp+"\n");
				
				$("#startIp").val(data.resultStartIp);
				$("#endIp").val(data.resultEndIp);
				
				}
		});
		
	}else{
		alert("IP를 입력하세요");
		$("#cidrInput").focus();
	}
		
	});
		
});

$(document).ready(function(){
	$("#ipRangeCheck").click(function(){
		if($("#startIp").val()){
			
			var query = {startIp:$("#startIp").val(),
						 endIp:$("#endIp").val(),
						 cityName:$("#cityName").val()};
			
			
			$.ajax({
					url :"/excel/ipRangeChk",
					type : "POST",
					data : query,
					success : function(data){
						
						if(data==-1){
							alert('IP범위가 중복 되었습니다.');
						
						}else if (data==1){
							alert('사용 가능한 IP 범위 입니다.');
						}
								
					}
			});
			
		}else{
			alert('IP 범위를 입력해 주세요.');
			$("#startIp").focus();
		}
			
	});
	
});

// ---------------------------------------------------------------------------------------------------------



$("#deleteExcel").click(function(){
	
	
	var rowData = new Array();
	var tdArr = new Array();
	var checkbox = $("input[name=checkBox]:checked");
	
	checkbox.each(function(i){
	
		var tr = checkbox.parent().parent().eq(i);
		var td = tr.children();
		
		rowData.push(tr.text());
		
		var ipIndex = td.eq(1).text();
		
		console.log(ipIndex);
		
		tdArr.push(ipIndex);
				
		$.ajax({
			type :'DELETE',
			url :'excel/delete/'+ipIndex,
			
			success : function(){
				alert('삭제되었습니다.');
				location.href='/';
			},
			error : function(request,status,error){
				
				alert('등록 실패');
				location.href='/';
				console.log(error);
				console.log(request);
				console.log(status);
				
			},
							
		})
			
	});
		
});

// --------------수정----------------------------------------

$("#updateExcel").click(function(){
	var rowData = new Array();
	var tdArr = new Array();
	var checkbox = $("input[name=checkBox]:checked");
	
	checkbox.each(function(i){
	
		var tr = checkbox.parent().parent().eq(i);
		var td = tr.children();
		
		rowData.push(tr.text());
		
		var ipIndex = td.eq(1).text();
		
		console.log(ipIndex);
		
		tdArr.push(ipIndex);
		
		
		$.ajax({
			type : 'GET',
			url : '/excel/excelOne/'+ipIndex,
			data : 'JSON',
			success:function(data){
				
				console.log(data);
				
				$("#publicIp1").val(data.publicIp);
				$("#publicIp1").attr("value",data.publicIp);

				$("#startIp1").val(data.startIp);
				$("#startIp1").attr("value",data.startIp);

				$("#endIp1").val(data.endIp);
				$("#endIp1").attr("value",data.endIp);
				
				$("#countryName1").val(data.countryName);
				$("#countryName1").attr("value",data.countryName);
				
				$("#cityName1").val(data.cityName);
				$("#cityName1").attr("value",data.cityName);
				
				
				
				
//			var test = "<input id='publicIp' name='publicIp' type='text' value='"+data.publicIp+"'><br>"+
//					    "<input id='startIp' name='startIp' type='text' value='"+data.ipStart+"'>"+
//                        "<input id='endIp' name='endIp' type='text' value='"+data.ipEnd+"' ><br>"+
//                        "<input id='countryName' name='countryName' value='"+data.countryName+"' > <br>"+
//                        "<input id='cityName' name='cityName' type='text' value='"+data.cityName+"'>"			
//				
//				
//                        $("#updateBody").append(test);
				
			},
			error:function(error){
				alert("error");
				console.log(error);
				
			}
	});
})
	
});


// --------------------------------------------------------------
$(document).ready(function(){

$("#updateExcelSubmit").click(function(){
	var rowData = new Array();
	var tdArr = new Array();
	var checkbox = $("input[name=checkBox]:checked");
	
	checkbox.each(function(i){
		var tr = checkbox.parent().parent().eq(i);
		var td = tr.children();
		
		rowData.push(tr.text());
		
		var ipIndex = td.eq(1).text();
		
		console.log(ipIndex);
		
		tdArr.push(ipIndex);
		
		$.ajax({
			type :'PUT',
			url : '/excel/excelUpdate/'+ipIndex,
			contentType : "application/json; charset=UTF-8",
			data : JSON.stringify({
				publicIp : $("#publicIp1").val(),
				startIp : $("#startIp1").val(),
				endIp : $("#endIp1").val(),
				countryName : $("#countryName1").val(),
				cityName : $("#cityName1").val()
				}),
			
				
				success : function (data){
					console.log(data);
					alert('수정 되었습니다.');
					location.href='/';
				},
				
				error:function(){
					alert('수정 실패');
					location.href='/';
				}	
								
			});
	})
		})
});












