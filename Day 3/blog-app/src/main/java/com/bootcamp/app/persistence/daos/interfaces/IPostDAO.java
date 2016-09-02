package com.bootcamp.app.persistence.daos.interfaces;

import java.util.Calendar;
import java.util.List;

import com.bootcamp.app.model.Post;

public interface IPostDAO extends IGenericDAO<Post, Long> {
	
	public List<Post> sortByNewest(int quantity, boolean asc);
	
	public List<Post> sortAlphaByTitle(int quantity, boolean asc);
	
	public List<Post> sortByLikes(int quantity, boolean asc);
	
	public List<Post> searchByTag(Long tagId);
	
	public List<Post> searchByText(String text);
	
	public List<Post> searchByUser(Long userId);
	
	public List<Post> searchBetweenDates(Calendar dateFrom, Calendar dateTo);
}
