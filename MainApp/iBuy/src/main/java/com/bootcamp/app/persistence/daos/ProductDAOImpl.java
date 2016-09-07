package com.bootcamp.app.persistence.daos;

import org.springframework.stereotype.Repository;

import com.bootcamp.app.model.Product;
import com.bootcamp.app.persistence.daos.interfaces.ProductDAO;

@Repository
public class ProductDAOImpl extends GenericDaoImpl<Product, Long> implements ProductDAO {

}
