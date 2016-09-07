package com.bootcamp.app.persistence.daos;

import org.springframework.stereotype.Repository;

import com.bootcamp.app.model.Reservation;
import com.bootcamp.app.persistence.daos.interfaces.ReservationDAO;

@Repository
public class ReservationDAOImpl extends GenericDaoImpl<Reservation, Long> implements ReservationDAO {

}
