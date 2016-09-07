package com.bootcamp.app.persistence.repositories;

import org.springframework.stereotype.Repository;

import com.bootcamp.app.model.Reservation;
import com.bootcamp.app.persistence.repositories.interfaces.ReservationDAO;

@Repository
public class ReservationDAOImpl extends GenericDaoImpl<Reservation, Long> implements ReservationDAO {

}
