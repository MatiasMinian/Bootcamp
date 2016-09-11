package com.bootcamp.app.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.app.model.Reservation;
import com.bootcamp.app.persistence.managers.ReservationManager;
import com.bootcamp.app.utils.DatesUtil;

@Service
public class ReservationService {
	
	@Autowired
	ReservationManager reservationManager;
	
	/* *** CONSTRUCTORS *** */
	
	public ReservationService() {}
	
	/* *** METHODS *** */
	
	public void updateReservations() {
		List<Reservation> reservations = reservationManager.findAllReservations();
		reservations.forEach(reservation -> {
			if (DatesUtil.differenceInDays(reservation.getDate(), Calendar.getInstance()) > 7) {
				reservationManager.deleteReservation(reservation);				
			}
		});		
	}
}
