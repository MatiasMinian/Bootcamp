package com.bootcamp.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.app.model.Product;
import com.bootcamp.app.model.Reservation;
import com.bootcamp.app.model.User;
import com.bootcamp.app.model.responses.MessageResponse;
import com.bootcamp.app.model.responses.ProductResponse;
import com.bootcamp.app.model.responses.SimpleProductResponse;
import com.bootcamp.app.persistence.managers.ProductManager;
import com.bootcamp.app.persistence.managers.ReservationManager;
import com.bootcamp.app.persistence.managers.UserManager;
import com.bootcamp.app.services.ReservationService;
import com.bootcamp.app.utils.Constants;

@RestController
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	@Autowired
	ReservationManager reservationManager;
	@Autowired
	UserManager userManager;
	@Autowired
	ProductManager productManager;
	
	@RequestMapping(value = "/api/update/reservations", method = RequestMethod.POST)
	public MessageResponse updateReservations() {
		reservationService.updateReservations();
		return new MessageResponse(Constants.MSG_SUCCESS, "Reservations were updated");
	}
	
	@RequestMapping(value = "/api/reserve/product", method = RequestMethod.POST)
	public MessageResponse reserveProduct(@RequestBody ProductResponse response) {
		User buyer = userManager.findUserById(response.getUser().getId());
		Product product = productManager.findProductById(response.getProduct().getId());
		Reservation reservation = new Reservation(buyer, product);
		reservationManager.saveNewReservation(reservation);
		return new MessageResponse(Constants.MSG_SUCCESS, "Reservation was made");
	}
	
	@RequestMapping(value = "/api/reservations/{id}", produces = "application/json", method = RequestMethod.GET)
	public List<SimpleProductResponse> getReservationsForUser(@PathVariable String id) {
		List<Reservation> reservations = reservationManager.getReservationsByUser(Long.valueOf(id));
		List<Product> products = new ArrayList<>();
		reservations.forEach(reservation -> products.add(reservation.getProduct()));
		List<SimpleProductResponse> productsResponse = new ArrayList<>();
		products.forEach(product -> {
			SimpleProductResponse simpleProduct = new SimpleProductResponse(product);
			productsResponse.add(simpleProduct);
		});
		return productsResponse;
	}
}
