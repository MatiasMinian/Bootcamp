package com.bootcamp.app.persistence.daos;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bootcamp.app.model.Category;
import com.bootcamp.app.persistence.daos.interfaces.CategoryDAO;

@Repository
public class CategoryDAOImpl extends GenericDaoImpl<Category, Long> implements CategoryDAO {

	@Override
	public Long categoryProductsQuantity(Long categoryId) {
		String sql = "SELECT count(*) FROM Product p WHERE p.category.id = :id";
		Query query = getSession().createQuery(sql).setParameter("id", categoryId);
		return (Long) query.uniqueResult();
	}
}
