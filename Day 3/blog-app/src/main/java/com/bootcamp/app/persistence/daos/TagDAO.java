package com.bootcamp.app.persistence.daos;

import com.bootcamp.app.model.Tag;
import com.bootcamp.app.persistence.daos.interfaces.ITagDAO;

public class TagDAO extends GenericDAO<Tag, Long> implements ITagDAO {

	public TagDAO(Class<Tag> type) {
		super(type);
	}
}
