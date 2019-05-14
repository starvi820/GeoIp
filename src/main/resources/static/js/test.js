


function continent(data){
	
	
	console.log(data.value);
		
	$.ajax({
		type:'GET',
		url:'/geoip/country/'+data.value,
		dataType:'json',
	    success : function(item){
	    	$('#country').html("");
	    	$('#city').html("");
	    	$('#countryVal').removeAttr("value");
	    	$('#cityVal').removeAttr("value");
	    	
	    	$('#country').append("<option value=''>== 선택 ==</option>");
	    	for(var i = 0; i<item.length; i++){
// console.log(item[i].countryName);
	    		
	    		$('#country').append("<option value='"+ item[i].countryCode+"' id='"+ "countryCode"+"'>"+ item[i].countryName+"</option>");
	    	}
			
		},
		
		error : function(request,status,error){
			console.log(request);
		}
		
		
	});
		
}



function country(data){
	
	console.log(data.value);
	
	$.ajax({
		type:'GET',
		url:'/geoip/cityFind/'+data.value,
		dataType:'json',
	    success : function(item){
	    	console.log(item.value);
	    	$('#city').html("");
	    	$('#cityVal').removeAttr("value");
	    	$('#city').append("<option value=''>== 선택 ==</option>");
	    	for(var i = 0; i<item.length; i++){
// console.log(item[i].countryName);
	    		
	    		$('#city').append("<option value='"+ item[i].cityName+"' id='"+ "countryCode"+"'>"+ item[i].cityName+"</option>");
	    	}
			
	    	$('#countryVal').attr("value",data.value);
	    	    	    	
		},
		
		error : function(request,status,error){
			console.log(request);
		}
				
	});
}


function city(data){
	
	$.ajax({
		type:'GET',
		url:'/geoip/cityLocation/'+data.value,
		dataType:'json',
	    success : function(item){
	    	console.log(item.value);
	    	$('#loc').html("");
	    	$('#locVal').removeAttr("value");
	    	$('#loc').append("<option value=''>== 선택 ==</option>");
	    	
	    	for(var i = 0; i<item.length; i++){
	    		$('#loc').append("<option value='"+ item[i].cityLocationCode+"' id='"+ "countryCode"+"'>"+ item[i].cityLocationName+"</option>");
	    			    		
	    	}
	    	
			
	    	$('#locVal').attr("value",item.value);
	    	$('#button').attr("value",data.value);
	    	$('#locCode').attr("value",item.value);
	    	
		},
		
		error : function(request,status,error){
			console.log(request);
		}
		
		
	});
}


function loc(data){
	
	console.log(data.value);
	
	$.ajax({
		type : 'GET',
		url : '/geoip/cityLocation/'+data.value,
		dataType : 'json',
		success : function(item){
			for(var i=0; i<item.lenth;i++){
				
				
			}
			
			$('#locVal').attr("value",data.value);
			$('#locCode').attr("value",data.value);
			
					
		},
		
		error : function(request,status,error){
			console.log(request);
		}
		
		
	});
}


function locCode(data){
	
	// 재 조회했을때 초기화
	  var container = L.DomUtil.get('map');
	   if(container != null){
		   
	   container._leaflet_id = null;
	   }
	   
	
	$.ajax({
		type : 'GET',
		url : '/geoip/cityLocationGeo/'+data.value,
		dataType : 'json',
		success : function(item){
			
			
			
			
			
			$("#longitude").attr("value",item[0].longitude);
			$("#latitude").attr("value",item[0].latitude);
			
				
			var longitude = $("#longitude").attr("value");
			var latitude = $("#latitude").attr("value");
			
			
			 L.mapquest.key = 'N3tsAk6E8W1TKsMiDADP5GeAY3bhK2Il';
			 var map = L.mapquest.map('map',{
				center : [latitude,longitude],
				layers : L.mapquest.tileLayer('map'),
				draggable: true,
				zoom : 13
			 });
			 			    
		   L.marker( [latitude,longitude],{
			    	icon : L.mapquest.icons.marker(),
			    	draggable: true    	
			    }).bindPopup('58.205.215.100').addTo(map);
		},
			
		error : function(){
			console.log("ERROR");
		}
				
	});
	
	
};


//==================================지도=====================================

window.onload = function(data) {
    L.mapquest.key = 'N3tsAk6E8W1TKsMiDADP5GeAY3bhK2Il';
    
    console.log(data);
    
    var map = L.mapquest.map('map', {
      center: [37,127],
      layers: L.mapquest.tileLayer('map'),
      zoom: 5
    });
    
       
    
    L.marker([37,127],{
    	icon : L.mapquest.icons.marker(),
    	draggable: false    	
    }).bindPopup('58.205.215.100').addTo(map);
    
    
               
}



function List(data){
	
	console.log(data);
	 $.ajax({
		type:'GET',
		url:'/geoip/geoipLst/'+data.value,
		headers : {"Content-Type" : "application/json;charset=UTF-8"},
		data:'json',
		success:function(item){
			
			$("#tbody2").html('');
			
			console.log(item);
	for(var i=0; i<item.length; i++){
						
	var test =	
			   "<tr><td><input type='checkbox' name='checkGeoip'>"+''+"</td>"+
			   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ item[i].ipIndex +"</td>" +
			   "<td style='width:100px; test-align: center; vertical-align: middle;' > "+ item[i].ipStart +"</td>" +
			   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ item[i].ipEnd +"</td>" +
			   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ item[i].countryName +"</td>"+
			   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ item[i].cityName +"</td>"+
			   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ item[i].cityLocationName +"</td>"+
			   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ item[i].publicIp +"</td>"+
			   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ item[i].privateIp +"</td>"+
			   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ item[i].companyName +"</td>"
			   "</tr>"
			   
			   $("#tbody2").append(test);
				
	$('#checkGeoip').attr("value",data.value);
					}
					
				},
				
		error:function(){
			alert("리스트 호출 실패");
		}
	});
	 
	};
	
	function ListAll(){
		 $.ajax({
			type:'GET',
			url:'/geoip/geoipLst',
			headers : {"Content-Type" : "application/json;charset=UTF-8"},
			data:'json',
			success:function(item){
				
				$("#tbody2").html('');
				
				console.log(item);
		for(var i=0; i<item.length; i++){
							
		var test =	
				   "<tr><td><input type='checkbox' name='checkGeoip'>"+''+"</td>"+
				   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ item[i].ipIndex +"</td>" +
				   "<td style='width:100px; test-align: center; vertical-align: middle;' > "+ item[i].ipStart +"</td>" +
				   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ item[i].ipEnd +"</td>" +
				   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ item[i].countryName +"</td>"+
				   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ item[i].cityName +"</td>"+
				   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ item[i].cityLocationName +"</td>"+
				   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ item[i].publicIp +"</td>"+
				   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ item[i].privateIp +"</td>"+
				   "<td style='width:100px; test-align: center; vertical-align: middle;' >"+ item[i].companyName +"</td>"
				   "</tr>"
				   
				   $("#tbody2").append(test);
					
						}
						
					},
					
			error:function(){
				alert("리스트 호출 실패");
			}
		});
		 
		};
	
$(document).ready(function(){
	
	$("#geoipInsertAjax").click(function(){
		
				
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
				"companyName" : $('#companyName').val(),
				
				
			}),
					
						
					
			success : function(data){
								
				alert('등록 되었습니다.');
				location.href='/geoip';
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


// ---------------------------------------------------------------------------------------------------------

$("#updateGeoip").click(function(){
	var rowData = new Array();
	var tdArr = new Array();
	var checkbox = $("input[name=checkGeoip]:checked");
	
	checkbox.each(function(i){
	
		var tr = checkbox.parent().parent().eq(i);
		var td = tr.children();
		
		rowData.push(tr.text());
		
		var ipIndex = td.eq(1).text();
		
		console.log(ipIndex);
		
		tdArr.push(ipIndex);
		
		
		$.ajax({
			type : 'GET',
			url : '/geoip/geoipOne/'+ipIndex,
			data : 'JSON',
			success:function(data){
				
				console.log(data);
				
				$("#publicIp2").val(data.publicIp);
				$("#publicIp2").attr("value",data.publicIp);

				$("#startIp2").val(data.ipStart);
				$("#startIp2").attr("value",data.ipStart);

				$("#endIp2").val(data.ipEnd);
				$("#endIp2").attr("value",data.ipEnd);
				
				$("#countryName2").val(data.countryName);
				$("#countryName2").attr("value",data.countryName);
				
				$("#cityName2").val(data.cityName);
				$("#cityName2").attr("value",data.cityName);
				
				$("#cityLocationName2").val(data.cityLocationName);
				$("#cityLocationName2").attr("value",data.cityLocationName);
				
				$("#companyName2").val(data.companyName);
				$("#companyName2").attr("value",data.companyName);
				
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

$("#updateSubmit").click(function(){
	var rowData = new Array();
	var tdArr = new Array();
	var checkbox = $("input[name=checkGeoip]:checked");
	
	checkbox.each(function(i){
		var tr = checkbox.parent().parent().eq(i);
		var td = tr.children();
		
		rowData.push(tr.text());
		
		var ipIndex = td.eq(1).text();
		
		console.log(ipIndex);
		
		tdArr.push(ipIndex);
		
		$.ajax({
			type :'PUT',
			url : '/geoip/geoipUpdate/'+ipIndex,
			contentType : "application/json; charset=UTF-8",
			data : JSON.stringify({
				publicIp : $("#publicIp2").val(),
				ipStart : $("#startIp2").val(),
				ipEnd : $("#endIp2").val(),
				countryName : $("#countryName2").val(),
				cityName : $("#cityName2").val(),
				cityLocationName : $("#cityLocationName2").val(),
				companyName : $("#companyName2").val()
				}),
							
				success : function (data){
					console.log(data);
					alert('수정 되었습니다.');
					location.href='/geoip';
				},
				
				error:function(){
					alert('수정 실패');
				}	
								
			});
	})
		})
});
// -------------------------------------------------------------------------

	$("#deleteGeoip").click(function(){
			
		
		var rowData = new Array();
		var tdArr = new Array();
		var checkbox = $("input[name=checkGeoip]:checked");
		
		checkbox.each(function(i){
		
			var tr = checkbox.parent().parent().eq(i);
			var td = tr.children();
			
			rowData.push(tr.text());
			
			var ipIndex = td.eq(1).text();
			
			console.log(ipIndex);
			
			tdArr.push(ipIndex);
			
			
			$.ajax({
				type :'DELETE',
				url :'geoip/delete/'+ipIndex,
				
				success : function(){
					alert('삭제되었습니다.');
					location.href='/geoip';
				},
				error : function(request,status,error){
					
					alert('등록 실패');
					location.href='/geoip';
					console.log(error);
					console.log(request);
					console.log(status);
					
				},
							
			})
		
		});
	});
	
	//-------------------------------------------유효 검사 -----------------------------------------------
	
	
	$(document).ready(function(){
		$("#duplication2").click(function(){
			
			
			if($("#publicIp2").val()){
			
			var query = {publicIp:$("#publicIp2").val()};
			
			$.ajax({
				url:"/excel/duplication",
				type:"POST",
				data : query,
				success : function(data){
					
					if(data==1){
						alert("Public IP가 중복되었습니다.");
						$("#publicIp2").val("");
						
					}else if (data==-1){
						alert("사용가능한 Public IP 입니다.");
						
					}
				}
			});
		}else{
			alert("사용할 Public IP 입력하세요");
			$("#publicIp2").focus();
		}
			
		});
			
	});



	$(document).ready(function(){
		$("#ipCheckPublicIp2").click(function(){
			
			
			if($("#publicIp2").val()){
			
			var query = {publicIp:$("#publicIp2").val()};
			
			$.ajax({
				url:"/excel/publicIpChk",
				type:"POST",
				data : query,
				success : function(data){
					
					if(data==-1){
						alert("유효하지 않은 IP 입니다.");
						$("#publicIp2").val("");
						
					}else if (data==1){
						alert("사용가능한 Public IP 입니다.");
						
					}
				}
			});
		}else{
			alert("사용할 Public IP 입력하세요");
			$("#publicIp2").focus();
		}
			
		});
			
	});



	$(document).ready(function(){
		$("#ipCheckStartIp2").click(function(){
			
			
			if($("#startIp2").val()){
			
			var query = {startIp:$("#startIp2").val()};
			
			$.ajax({
				url:"/excel/startIpChk",
				type:"POST",
				data : query,
				success : function(data){
					
					if(data==-1){
						alert("유효하지 않은 시작 IP 입니다.");
						$("#startIp2").val("");
						
					}else if (data==1){
						alert("사용가능한 시작 IP 입니다.");
						
					}
				}
			});
		}else{
			alert("사용할 시작 IP 입력하세요");
			$("#startIp2").focus();
		}
			
		});
			
	});




	$(document).ready(function(){
		$("#ipCheckEndIp2").click(function(){
			
			
			if($("#endIp2").val()){
			
			var query = {endIp:$("#endIp2").val()};
			
			$.ajax({
				url:"/excel/endIpChk",
				type:"POST",
				data : query,
				success : function(data){
					
					if(data==-1){
						alert("유효하지 않은 끝 IP 입니다.");
						$("#endIp2").val("");
						
					}else if (data==1){
						alert("사용가능한 끝 IP 입니다.");
						
					}
				}
			});
		}else{
			alert("사용할 끝 IP 입력하세요");
			$("#endIp2").focus();
		}
			
		});
			
	});





	$(document).ready(function(){
		$("#cidrAddressChk2").click(function(){
			
			
			if($("#cidrInput2").val()){
			
			var query = {cidrInput:$("#cidrInput2").val()};
			
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
					
					$("#startIp2").val(data.resultStartIp);
					$("#endIp2").val(data.resultEndIp);
					
					}
			
			
			
			});
			
		}else{
			alert("IP를 입력하세요");
			$("#cidrInput2").focus();
		}
			
		});
			
	});



	$(document).ready(function(){
		$("#ipRangeCheck2").click(function(){
			if($("#startIp2").val()){
				
				var query = {startIp:$("#startIp2").val(),
							 endIp:$("#endIp2").val(),
							 cityName:$("#cityName2").val()};
				
				
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
				$("#startIp2").focus();
			}
				
		});
		
	});

	


	
	
	
	
	
	
