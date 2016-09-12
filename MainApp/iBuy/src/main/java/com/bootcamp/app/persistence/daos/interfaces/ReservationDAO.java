package com.bootcamp.app.persistence.daos.interfaces;

import java.util.List;

import com.bootcamp.app.model.Reservation;

public interface ReservationDAO extends GenericDAO<Reservation, Long> {
	
	public List<Reservation> getByUser(Long userId);

}
