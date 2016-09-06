package com.bootcamp.app.persistence.daos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bootcamp.app.model.User;

public interface UserDAO extends PagingAndSortingRepository<User, Long> {

}
