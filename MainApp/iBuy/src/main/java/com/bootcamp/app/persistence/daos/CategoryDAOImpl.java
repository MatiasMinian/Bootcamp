package com.bootcamp.app.persistence.daos;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bootcamp.app.model.Category;
import com.bootcamp.app.persistence.daos.interfaces.CategoryDAO;

@Repository
public class CategoryDAOImpl extends GenericDaoImpl<Category, Long> implements CategoryDAO {

	@Override
	public Long categoryProductsQuantity(Long categoryId) {
		String sql = "SELECT count(*) FROM Product p WHERE p.category.id = :id";
		Query query = (Query) getSession().createQuery(sql).setParameter("id", categoryId.toString());
		return ((Long) query.getSingleResult());
	}
}
