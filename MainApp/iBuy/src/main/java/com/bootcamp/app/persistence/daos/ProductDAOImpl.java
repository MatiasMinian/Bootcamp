package com.bootcamp.app.persistence.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import com.bootcamp.app.model.Product;
import com.bootcamp.app.persistence.daos.interfaces.ProductDAO;

@Repository
public class ProductDAOImpl extends GenericDaoImpl<Product, Long> implements ProductDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> searchProduct(String searchText) {
		List<Product> products = new ArrayList<>();
		try {
			FullTextSession fullTextSession = Search.getFullTextSession(getSession());

			QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Product.class).get();
			org.apache.lucene.search.Query query = qb.keyword().onFields("name", "description")
					.matching(searchText).createQuery();

			Query hibQuery = fullTextSession.createFullTextQuery(query, Product.class);

			products = hibQuery.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<Product> sortByCheapest() {
		String sql = "SELECT p FROM Product p ORDER BY p.price asc";
		Query query = getSession().createQuery(sql);
		return findMany(query);
	}

	@Override
	public List<Product> sortByPriciest() {
		String sql = "SELECT p FROM Product p ORDER BY p.price desc";
		Query query = getSession().createQuery(sql);
		return findMany(query);
	}
	
	@Override
	public List<Product> filterByCategory(Long categoryId) {
		String sql = "SELECT p FROM Product p WHERE p.category.id = :id";
		Query query = getSession().createQuery(sql).setParameter("id", categoryId);
		return findMany(query);
	}
}