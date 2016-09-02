package com.bootcamp.app.persistence.managers;

import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;

import com.bootcamp.app.model.Post;
import com.bootcamp.app.persistence.HibernateUtil;
import com.bootcamp.app.persistence.daos.interfaces.IPostDAO;

public class PostManager {

	// TODO Should I use Dependency Injection
	private IPostDAO postDAO;

	public void saveNewPost(Post post) {
		try {
			HibernateUtil.beginTransaction();
			postDAO.save(post);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}

	public void updatePost(Post post) {
		try {
			HibernateUtil.beginTransaction();
			postDAO.update(post);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}

	public void deletePost(Post post) {
		try {
			HibernateUtil.beginTransaction();
			postDAO.delete(post);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}

	public Post findPostById(Long id) {
		Post post = null;
		try {
			HibernateUtil.beginTransaction();
			post = (Post) postDAO.findById(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return post;
	}
	
	public List<Post> sortPostsByNewest(int quantity, boolean asc) {
		List<Post> posts = null;
		try {
			HibernateUtil.beginTransaction();
			posts = postDAO.sortByNewest(quantity, asc);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return posts;		
	}
	
	public List<Post> sortPostsByTitleAtoZ(int quantity, boolean asc) {
		List<Post> posts = null;
		try {
			HibernateUtil.beginTransaction();
			posts = postDAO.sortAlphaByTitle(quantity, asc);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return posts;
	}
	
	public List<Post> sortPostsByLikes(int quantity, boolean asc) {
		List<Post> posts = null;
		try {
			HibernateUtil.beginTransaction();
			posts = postDAO.sortByLikes(quantity, asc);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return posts;		
	}
	
	public List<Post> searchPostsByTag(Long tagId) {
		List<Post> posts = null;
		try {
			HibernateUtil.beginTransaction();
			posts = postDAO.searchByTag(tagId);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return posts;				
	}
	
	public List<Post> searchPostsByText(String text) {
		List<Post> posts = null;
		try {
			HibernateUtil.beginTransaction();
			posts = postDAO.searchByText(text);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return posts;
	}
	
	public List<Post> searchPostsByUser(Long userId) {
		List<Post> posts = null;
		try {
			HibernateUtil.beginTransaction();
			posts = postDAO.searchByUser(userId);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return posts;		
	}
	
	public List<Post> searchPostsBetweenDates(Calendar dateFrom, Calendar dateTo) {
		List<Post> posts = null;
		try {
			HibernateUtil.beginTransaction();
			posts = postDAO.searchBetweenDates(dateFrom, dateTo);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return posts;		
	}
}
