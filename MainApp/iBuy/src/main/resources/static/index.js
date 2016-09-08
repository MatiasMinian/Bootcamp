$(function(){
	$("#user_button").click(function(){
		$.get("http://localhost:8080/api/users", function(data, status){
			$.each(data, function(index, user){
				$("#main_data").append(user.username)
			})
			//$("#main_data").text(JSON.stringify(data));	
			alert(status);
		}, "json")		
	})	
});