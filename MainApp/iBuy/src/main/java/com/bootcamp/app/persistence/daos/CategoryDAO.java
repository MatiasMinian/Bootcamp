package com.bootcamp.app.persistence.daos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bootcamp.app.model.Category;

public interface CategoryDAO extends PagingAndSortingRepository<Category, Long> {

}
