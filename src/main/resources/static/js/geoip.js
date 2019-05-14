$(document).ready(function() {
	});



function country(data){
		
	$.ajax({
		type:'GET',
		url:'/geoip/continent',
		dataType:'json',
		success : function(data){
			$("#continentOp").html('');
			for(var i=0; i<data.length; i++){
				var test = "<option value='"+data[i].continentName+"'>"+data[i].continentName+"</option>";
				
		
		$("#continentOp").append(test);
				
			}
					
		},
		
		error : function(request,status,error){
			console.log(request);
		}		
		
	});
		
}







