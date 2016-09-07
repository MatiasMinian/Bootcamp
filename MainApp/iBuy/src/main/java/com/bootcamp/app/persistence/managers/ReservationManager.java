package com.bootcamp.app.persistence.managers;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootcamp.app.model.Reservation;
import com.bootcamp.app.persistence.HibernateUtil;
import com.bootcamp.app.persistence.daos.interfaces.ReservationDAO;

@Component
public class ReservationManager {

	@Autowired
	ReservationDAO reservationDAO;

	/* *** CONSTRUCTORS *** */

	public ReservationManager() {
	}

	public ReservationManager(ReservationDAO reservationDAO) {
		this.reservationDAO = reservationDAO;
	}

	/* *** METHODS *** */

	public Long saveNewReservation(Reservation reservation) {
		Long id = null;
		try {
			HibernateUtil.beginTransaction();
			id = reservationDAO.save(reservation);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		System.out.println("Reservation was saved successfully");
		return id;
	}

	public void updateReservation(Reservation reservation) {
		try {
			HibernateUtil.beginTransaction();
			reservationDAO.update(reservation);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		System.out.println("Reservation was updated successfully");
	}

	public void deleteReservation(Reservation reservation) {
		try {
			HibernateUtil.beginTransaction();
			reservationDAO.delete(reservation);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		System.out.println("Reservation was deleted successfully");
	}

	public Reservation findReservationById(Long id) {
		Reservation reservation = null;
		try {
			HibernateUtil.beginTransaction();
			reservation = (Reservation) reservationDAO.findById(Reservation.class, id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		System.out.println("Reservation was found successfully");
		return reservation;
	}

	/* *** GETTERS & SETTERS *** */

	public ReservationDAO getReservationDAO() {
		return reservationDAO;
	}

	public void setReservationDAO(ReservationDAO reservationDAO) {
		this.reservationDAO = reservationDAO;
	}
}