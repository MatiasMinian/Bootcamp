// Generic Functions

function showUsers() {
	$("#main").load("show_users.html");
	$.get("http://localhost:8080/api/users", function(data, status){
		var mainTableBody = $("#main_table_body");
		$.each(data, function(index, user){
			var buttonStatus = "";
			if (!user.deleteable) {
				buttonStatus = "disabled"								
			}
			mainTableBody.append("<tr><td><button type=\"button\" class=\"btn btn-primary\">Impersonate</button></td>\
				<td>"+user.username+"</td><td>"+user.firstName+"</td><td>"+user.lastName+"</td>\
				<td><button type=\"button\" class=\"btn btn-default\">Edit</button></td>\
				<td><button type=\"button\" class=\"btn btn-danger "+buttonStatus+"\">Delete</button></td></tr>"
			);
		});
	}, "json");	
}

function getFormData($form){
	var unindexed_array = $form.serializeArray();
	var indexed_array = {};
	
	$.map(unindexed_array, function(n, i){
		indexed_array[n['name']] = n['value'];
	});
	return indexed_array;
};

// Functions from Users

$(function(){
	showUsers();
});

// Functions from Create User
