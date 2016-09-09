// GENERIC FUNCTIONS

function showUsers() {
	$("#main").load("show_users.html");
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
			tdImpersonateButton.append(impersonateButton);
			var tdUsername = $("<td>"+user.username+"</td>");
			var tdFirstName = $("<td>"+user.firstName+"</td>");
			var tdLastName = $("<td>"+user.lastName+"</td>");
			var tdEditButton = $("<td></td>");
			var editButton = $("<button type=\"button\" class=\"btn btn-default\">Edit</button>");
			editButton.click(function(){
				onClickOnEditButton(user);
			});
			tdEditButton.append(editButton);
			var tdDeleteButton = $("<td></td>");
			var deleteButton = $("<button type=\"button\" class=\"btn btn-danger "+buttonStatus+"\">Delete</button>");
			deleteButton.click(function() {
				onClickOnDeleteButton(user, tr);
			});
			tdDeleteButton.append(deleteButton);
			tr.append(tdImpersonateButton, tdUsername, tdFirstName, tdLastName, tdEditButton, tdDeleteButton);
			mainTableBody.append(tr);		
		});
	}, "json");	
}

function onClickOnEditButton(user) {
	$("#main").load("edit_user.html", function(){
		//$("#edit_user_id").val(user.id);
		$("#edit_username_input").val(user.username);
		$("#edit_email_input").val(user.email);
		$("#edit_first_name_input").val(user.firstName);
		$("#edit_last_name_input").val(user.lastName);
		$("#edit_birth_date_input").val(user.birthDate);
		$("#edit_user_cancel").click(function() {
			showUsers();
		});
		$("#edit_user_update").click(function(){
			var frm = $("#form_edit_user");
			var parsedForm = getFormData(frm);
			parsedForm["id"] = user.id;
			var formData = JSON.stringify(parsedForm);			
			$.ajax({
				type: "POST",
				contentType: "application/json",
				data: formData,
				url: "http://localhost:8080/api/update/user"
			}).then(function(data, status, jqxhr) {
				if (status == "success") {
					$("#edit_user_response").text(data);
				} else {
					$("#edit_user_response").text("A problem has occured. Try again");
				}
			});
		});
	});	
}

function onClickOnDeleteButton(user, tr) {
	//var response = {id:userId};
	//response["id"] = id;
	//var data = JSON.stringify(response);
	//var id = 2;
//	alert(data);
	
	$.ajax({
		type: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(user),	
		url: "http://localhost:8080/api/delete/user"
	}).then(function(data, status, jqxhr) {
		if (status == "success") {
			tr.fadeOut(400, function(){
	            tr.remove();
	        });			
		} else {
			alert("A problem has occured. Try again");
		}
	});
}

function getFormData($form){
	var unindexed_array = $form.serializeArray();
	var indexed_array = {};
	
	$.map(unindexed_array, function(n, i){
		indexed_array[n['name']] = n['value'];
	});
	return indexed_array;
};

// FUNCTIONS FROM USERS


$(function(){
	showUsers();
});

// FUNCTIONS FROM CREATE USER
