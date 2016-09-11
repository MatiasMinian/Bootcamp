$(function() {
	setProfileName();
});

function setProfileName() {
	var user = JSON.parse(localStorage.getItem("user_session"));
	$("#user_profile_text").append("<span class=\"glyphicon glyphicon-user\"></span>  " + user.username);
}

function onClickonUserProfile() {
	
}

function onClickOnLogout() {
	window.location.href = "index.html";
}