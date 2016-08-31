package com.bootcamp.app.persistance.daos;

import com.bootcamp.app.Post;

public class PostDAO extends GenericDAO<Post, Long> {

	public PostDAO(Class<Post> type) {
		super(type);
	}

}
