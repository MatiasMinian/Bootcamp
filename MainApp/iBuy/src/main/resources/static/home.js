$(function() {
	var user = JSON.parse(localStorage.getItem("user_session"));
	alert(user.id + user.username);
});