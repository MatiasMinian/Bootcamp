$(function() {
	$("#sign_in_button").click(function() {
		var email = $("#input_email").val();
		var pass = $("#input_password").val();
		$.post(
			"/api", 
			{
			email: email,
			password: pass 
			}).done(function(data) {
				alert(data.)
			});
	});
});