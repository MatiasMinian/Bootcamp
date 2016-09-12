package com.bootcamp.app.persistence.daos;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bootcamp.app.model.Reservation;
import com.bootcamp.app.persistence.daos.interfaces.ReservationDAO;

@Repository
public class ReservationDAOImpl extends GenericDaoImpl<Reservation, Long> implements ReservationDAO {

	@Override
	public List<Reservation> getByUser(Long userId) {
		String sql = "SELECT r FROM Reservation r WHERE r.product.owner.id = :id";
		Query query = getSession().createQuery(sql).setParameter("id", userId);
		return findMany(query);
	}
}
