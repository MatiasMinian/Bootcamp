package com.bootcamp.app.persistence.daos;

import org.springframework.stereotype.Repository;

import com.bootcamp.app.model.Category;
import com.bootcamp.app.persistence.daos.interfaces.CategoryDAO;

@Repository
public class CategoryDAOImpl extends GenericDaoImpl<Category, Long> implements CategoryDAO {

}
