package com.bootcamp.app.persistence.daos;

import java.util.Calendar;
import java.util.List;

import com.bootcamp.app.model.Post;
import com.bootcamp.app.persistence.daos.interfaces.IPostDAO;

public class PostDAO extends GenericDAO<Post, Long> implements IPostDAO {

	public PostDAO(Class<Post> type) {
		super(type);
	}
	
	// TODO implement methods

	@Override
	public List<Post> sortByNewest(int quantity, boolean asc) {
		return null;
	}

	@Override
	public List<Post> sortAlphaByTitle(int quantity, boolean asc) {
		return null;
	}

	@Override
	public List<Post> sortByLikes(int quantity, boolean asc) {
		return null;
	}

	@Override
	public List<Post> searchByTag(Long tagId) {
		return null;
	}

	@Override
	public List<Post> searchByText(String text) {
		return null;
	}

	@Override
	public List<Post> searchByUser(Long userId) {
		return null;
	}

	@Override
	public List<Post> searchBetweenDates(Calendar dateFrom, Calendar dateTo) {
		return null;
	}
}
