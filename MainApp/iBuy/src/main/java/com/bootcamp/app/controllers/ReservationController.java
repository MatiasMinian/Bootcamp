package com.bootcamp.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.app.model.responses.MessageResponse;
import com.bootcamp.app.services.ReservationService;
import com.bootcamp.app.utils.Constants;

@RestController
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	@RequestMapping(value = "/api/update/reservations", method = RequestMethod.POST)
	public MessageResponse updateReservations() {
		reservationService.updateReservations();
		return new MessageResponse(Constants.MSG_SUCCESS, "Reservations were updated");
	} 
}
