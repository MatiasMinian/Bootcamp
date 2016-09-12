$(function() {
	setProfileName();
	setData();
});

function setProfileName() {
	var user = JSON.parse(localStorage.getItem("user_session"));
	$("#user_profile_text").append("<span class=\"glyphicon glyphicon-user\"></span>  " + user.username);
}

function setData() {
	var user = JSON.parse(localStorage.getItem("user_session"));
	$("#edit_username_input").val(user.username);
	$("#edit_email_input").val(user.email);
	$("#edit_first_name_input").val(user.firstName);
	$("#edit_last_name_input").val(user.lastName);
	$("#edit_birth_date_input").val(user.birthDate);
	$("#edit_user_cancel").click(function() {
		window.location.href = "home.html";
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
				localStorage.setItem("user_session", formData);
				$("#edit_user_response").text(data);
			} else {
				$("#edit_user_response").text("A problem has occured. Try again");
			}
		});
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