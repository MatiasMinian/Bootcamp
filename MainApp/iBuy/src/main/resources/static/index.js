$(function(){
	$("#user_button").click(function(){
		$.get("http://localhost:8080/api/users", function(data, status){
			$("p").text(data);	
			alert(status);
		}, "json")		
	})	
});