$(function() {
	populateSelect();
	setClickOnCreate();
	$("#new_product_cancel").click(function() {
		window.location.href = "my_products.html";
	});
	$("input").focus(function() {
		$("#create_product_response").text("");
	});
});

function populateSelect() {
	$.get("http://localhost:8080/api/categories", function(data, status){
		var categoryInput = $("#category_input");
		$.each(data, function(index, category){
			categoryInput.append("<option value=" + category.id + ">" + category.name + "</option>");
		});
	}, "json");
}

function setClickOnCreate() {
	// Function for create product
	$(function() {
		$("#new_product_create").click(function() {
			var user = JSON.parse(localStorage.getItem("user_session"));
			var frm = $("#form_create_product");
			var parsedForm = getFormData(frm);
			parsedForm["userId"] = user.id;
			var formData = JSON.stringify(parsedForm);
			$.ajax({
				type: "POST",
				contentType: "application/json",
				data: formData,
				url: "http://localhost:8080/api/create/product"
			}).then(function(data, status, jqxhr) {
				if (status == "success") {
					$("#create_product_response").text(data.message);
					$("input").val("");
				} else {
					$("#create_user_response").text("A problem has occured. Try again");
				}
			});
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