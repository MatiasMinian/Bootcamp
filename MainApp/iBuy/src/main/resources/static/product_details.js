$(function() {
	setData();
});

function setData() {
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "http://localhost:8080/api/product/" + localStorage.getItem("product_id")
	}).then(function(data, status, jqxhr) {
		if (status == "success") {
			$("#product_title").text(data.product.name);
			$("#product_category").text(data.product.categoryName);
			alert(data.product.isNew);
			if (data.product.isNew) {
				$("#product_condition").text("New");
			} else {
				$("#product_condition").text("Used");
			}
			$("#product_price").append(data.product.price);
			$("#product_description").text(data.product.description);
			$("#user_name").text(data.user.firstName);
			$("#user_last_name").text(data.user.lastName);
			$("#username").text(data.user.username);
			$("#user_email").text(data.user.email);
			alert(data.product.reserved);
			if (data.product.reserved) {
				$("#button_reserve").addClass("disabled");
			} else {
				$("#button_reserve").click(function() {
					$.ajax({
						type: "POST",
						contentType: "application/json",
						data: JSON.stringify(data),
						url: "http://localhost:8080/api/reserve/product"
					}).then(function(data, status, jqxhr) {
						if (status == "success") {
							alert(data.message);
						} else {
							$("#edit_user_response").text("A problem has occured. Try again");
						}
					});
				});
			}			
		} else {
			alert("A problem has occured. Try again");
		}
	});
}