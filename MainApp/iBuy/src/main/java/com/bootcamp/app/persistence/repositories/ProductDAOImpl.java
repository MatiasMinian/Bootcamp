package com.bootcamp.app.persistence.repositories;

import org.springframework.stereotype.Repository;

import com.bootcamp.app.model.Product;
import com.bootcamp.app.persistence.repositories.interfaces.ProductDAO;

@Repository
public class ProductDAOImpl extends GenericDaoImpl<Product, Long> implements ProductDAO {

}
