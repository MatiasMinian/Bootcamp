$(function() {
	showProducts();
	setClickOnCreateProduct();
});

function showProducts() {
	var user = JSON.parse(localStorage.getItem("user_session"));
	$.get("http://localhost:8080/api/products/"+user.id, function(data, status){
		var mainTableBody = $("#main_table_body");
		$.each(data, function(index, product){
			var tr = $("<tr></tr>");
			var tdName = $("<td>"+product.name+"</td>");
			var tdCategory = $("<td>"+product.categoryName+"</td>");
			var tdPrice = $("<td>"+product.price+"</td>");
			var condition;
			if (product.isNew) {
				condition = "New"
			} else {
				condition = "Used"
			}
			var tdConditon = $("<td>"+condition+"</td>");
			var tdSoldButton = $("<td></td>");
			var soldButton = $("<button type=\"button\" class=\"btn btn-primary\">Sold</button>");
			soldButton.click(function() {
				productSold(product, tr)								
			});
			tdSoldButton.append(soldButton);
			tr.append(tdName, tdCategory, tdPrice, tdConditon, tdSoldButton);
			mainTableBody.append(tr);
		});
	}, "json");
}

function productSold(product, tr) {
	$.ajax({
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify(product),
		url: "http://localhost:8080/api/product/sold"
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
			$("#edit_user_response").text("A problem has occured. Try again");
		}
	});
}

function setClickOnCreateProduct() {
	$("#button_create_product").click(function() {
		window.location.href = "create_product.html";		
	});	
}