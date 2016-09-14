$(function() {
	setProfileName();
	showProducts();
	setClickOnSearchButton();
	setSorters();
});

function setProfileName() {
	var user = JSON.parse(localStorage.getItem("user_session"));
	$("#user_profile_text").append("<span class=\"glyphicon glyphicon-user\"></span>  " + user.username);
}

function onClickOnLogout() {
	window.location.href = "index.html";
}

function showProducts() {
	$.get("http://localhost:8080/api/products/sortby=cheapest", function(data, status){
		var mainTableBody = $("#main_table_body");
		$("#main_table_body").empty();
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
			var tdDetailsButton = $("<td></td>");
			var detailsButton = $("<button type=\"button\" class=\"btn btn-primary\">Details</button>");
			detailsButton.click(function() {
				localStorage.setItem("product_id", product.id);
				window.location.href = "product_details.html";
			});
			tdDetailsButton.append(detailsButton);
			tr.append(tdName, tdCategory, tdPrice, tdConditon, tdDetailsButton);
			mainTableBody.append(tr);
		});
	}, "json");
}

function setClickOnSearchButton() {
	$("#button_search").click(function() {
		var search = $("#search_product").val();
		$("#main_table_body").empty();
		$.get("http://localhost:8080/api/products/search="+search, function(data, status){
			$("#actual_search").append("<p class=\"text-center\">"+search+" <a id=\"remove_search\" href=\"#\">\
	          <span class=\"glyphicon glyphicon-remove\"></span></a></p>");
			$("#remove_search").click(function() {
				$("#sorters").show();
				$("#filters").show();
				$("#actual_search").empty();
				showProducts();												
			});
			$("#sorters").hide();
			$("#filters").hide();
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
				var tdDetailsButton = $("<td></td>");
				var detailsButton = $("<button type=\"button\" class=\"btn btn-primary\">Details</button>");
				detailsButton.click(function() {
					localStorage.setItem("product_id", product.id);
					window.location.href = "product_details.html";
				});
				tdDetailsButton.append(detailsButton);
				tr.append(tdName, tdCategory, tdPrice, tdConditon, tdDetailsButton);
				mainTableBody.append(tr);
			});
		}, "json");
	});
}

function setSorters() {
	$("input[name='sort_by']").on("change", function () {
		$.get("http://localhost:8080/api/products/sortby="+this.value, function(data, status){
			var mainTableBody = $("#main_table_body");
			$("#main_table_body").empty();
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
				var tdDetailsButton = $("<td></td>");
				var detailsButton = $("<button type=\"button\" class=\"btn btn-primary\">Details</button>");
				detailsButton.click(function() {
					localStorage.setItem("product_id", product.id);
					window.location.href = "product_details.html";
				});
				tdDetailsButton.append(detailsButton);
				tr.append(tdName, tdCategory, tdPrice, tdConditon, tdDetailsButton);
				mainTableBody.append(tr);
			});
		}, "json");
		
	});
}