package com.bootcamp.app.persistence.daos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bootcamp.app.model.Product;

public interface ProductDAO extends PagingAndSortingRepository<Product, Long> {

}
