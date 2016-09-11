// FUNCTIONS FROM USERS

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
	}, "json");	
}

function onClickOnEditUser(user) {
	$("#main").load("edit_user.html", function(){
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

function onClickOnDeleteUser(user, tr) {
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

function updateReservations() {
	$.ajax({
		type: "POST",
		contentType: "application/json; charset=utf-8",
		url: "http://localhost:8080/api/update/reservations"
	}).then(function(data, status, jqxhr) {
		if (status == "success") {
			alert(data.message);					
		} else {
			alert("A problem has occured. Try again");
		}
	});	
}

$(function(){
	showUsers();
});

// FUNCTIONS FROM CATEGORIES

function showCategories() {
	$("#main").load("show_categories.html");
	$.get("http://localhost:8080/api/categories", function(data, status){
		var mainTableBody = $("#main_table_body");
		$.each(data, function(index, category){
			var tr = $("<tr></tr>");
			var tdName = $("<td>"+category.name+"</td>");
			var tdDescription = $("<td>"+category.description+"</td>");
			var tdEditButton = $("<td></td>");
			var editButton = $("<button type=\"button\" class=\"btn btn-default\">Edit</button>");
			editButton.click(function(){
				onClickOnEditCategory(category);
			});
			tdEditButton.append(editButton);
			var tdDeleteButton = $("<td></td>");
			var deleteButton = $("<button type=\"button\" class=\"btn btn-danger\">Delete</button>");
			deleteButton.click(function() {
				onClickOnDeleteCategory(category, tr);
			});
			tdDeleteButton.append(deleteButton);
			tr.append(tdName, tdDescription, tdEditButton, tdDeleteButton);
			mainTableBody.append(tr);
		});
	}, "json");
}

function onClickOnEditCategory(category) {
	$("#main").load("edit_category.html", function(){
		$("#edit_name_input").val(category.name);
		$("#edit_description_input").val(category.description);
		$("#edit_category_cancel").click(function() {
			showCategories();
		});
		$("#edit_category_update").click(function(){
			var frm = $("#form_edit_category");
			var parsedForm = getFormData(frm);
			parsedForm["id"] = category.id;
			var formData = JSON.stringify(parsedForm);			
			$.ajax({
				type: "POST",
				contentType: "application/json",
				data: formData,
				url: "http://localhost:8080/api/update/category"
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

function onClickOnDeleteCategory(category, tr) {
	$.ajax({
		type: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(category),	
		url: "http://localhost:8080/api/delete/category"
	}).then(function(data, status, jqxhr) {
		if (status == "success") {
			if (data.status == "success") {
				tr.fadeOut(400, function(){
		            tr.remove();
		        });
			} else {
				alert(data.message);				
			}						
		} else {
			alert("A problem has occured. Try again");
		}
	});
}

// GENERIC FUNCTIONS

function onClickOnUsersTab() {
	var usersTab = $("#users_tab");
	if(!usersTab.hasClass("active")) {
		usersTab.addClass("active");
		$("#categories_tab").removeClass("active");
		showUsers();
	}
}

function onClickOnCategoriesTab() {
	var categoriesTab = $("#categories_tab");
	if(!categoriesTab.hasClass("active")) {
		categoriesTab.addClass("active")
		$("#users_tab").removeClass("active");
		showCategories();
	}
}

function getFormData($form){
	var unindexed_array = $form.serializeArray();
	var indexed_array = {};
	
	$.map(unindexed_array, function(n, i){
		indexed_array[n['name']] = n['value'];
	});
	return indexed_array;
};


