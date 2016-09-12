$(function() {
	showReservations();
});

function showReservations() {
	var user = JSON.parse(localStorage.getItem("user_session"));
	$.get("http://localhost:8080/api/reservations/"+user.id, function(data, status){
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
}