package com.bootcamp.app.persistence.daos;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bootcamp.app.model.Product;
import com.bootcamp.app.persistence.daos.interfaces.ProductDAO;

@Repository
public class ProductDAOImpl extends GenericDaoImpl<Product, Long> implements ProductDAO {

	@Override
	public List<Product> findByWord(String word) {
		String sql = "SELECT p FROM Product p WHERE CHARINDEX(:word, p.name) > 0 or CHARINDEX(:word, p.description) > 0";
		Query query = (Query) getSession().createQuery(sql).setParameter("word", word);
		return findMany(query);
	}

	@Override
	public List<Product> sortByCheapest() {
		String sql = "SELECT p FROM Product ORDER BY p.price asc";
		Query query = (Query) getSession().createQuery(sql);
		return findMany(query);
	}

	@Override
	public List<Product> sortByPriciest() {
		String sql = "SELECT p FROM Product ORDER BY p.price desc";
		Query query = (Query) getSession().createQuery(sql);
		return findMany(query);
	}

	@Override
	public List<Product> filterByCategory(Long categoryId) {
		String sql = "SELECT p FROM Product WHERE p.category.id = :id";
		Query query = (Query) getSession().createQuery(sql).setParameter("id", categoryId);
		return findMany(query);
	}
}
