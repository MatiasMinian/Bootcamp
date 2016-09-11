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

function showProducts() {/*
	$.get("http://localhost:8080/api/users", function(data, status){
		var mainTableBody = $("#main_table_body");
		$.each(data, function(index, user){
			var buttonStatus = "";
			if (!user.deleteable) {
				buttonStatus = "disabled"								
			}
			var tr = $("<tr></tr>");
			var tdImpersonateButton = $("<td></td>");
			var impersonateButton = $("<button type=\"button\" class=\"btn btn-primary\">Impersonate</button>");
			impersonateButton.click(function() {
				localStorage.setItem("user_session", JSON.stringify(user));
				window.location.href = "home.html";
			});
			tdImpersonateButton.append(impersonateButton);
			var tdUsername = $("<td>"+user.username+"</td>");
			var tdFirstName = $("<td>"+user.firstName+"</td>");
			var tdLastName = $("<td>"+user.lastName+"</td>");
			var tdEditButton = $("<td></td>");
			var editButton = $("<button type=\"button\" class=\"btn btn-default\">Edit</button>");
			editButton.click(function(){
				onClickOnEditUser(user);
			});
			tdEditButton.append(editButton);
			var tdDeleteButton = $("<td></td>");
			var deleteButton = $("<button type=\"button\" class=\"btn btn-danger "+buttonStatus+"\">Delete</button>");
			if (user.deletable) {
				deleteButton.click(function() {
					onClickOnDeleteUser(user, tr);
				});
			}
			tdDeleteButton.append(deleteButton);
			tr.append(tdImpersonateButton, tdUsername, tdFirstName, tdLastName, tdEditButton, tdDeleteButton);
			mainTableBody.append(tr);		
		});
	}, "json");	*/	
}