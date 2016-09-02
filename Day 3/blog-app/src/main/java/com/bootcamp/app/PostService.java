package com.bootcamp.app;

import java.util.Set;

import com.bootcamp.app.model.Group;
import com.bootcamp.app.model.Post;
import com.bootcamp.app.model.Tag;
import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.managers.PostManager;

public class PostService {
	
	// TODO Should I use Dependency Injection
	private PostManager postManager;
	private SubscriptionsService subscriptionsService;
	
	public void addLike(User user, Post post) {
		post.getLikes().add(user);
		postManager.updatePost(post);		
	}
	
	public Post createPost(String title, String text, Set<Tag> tags, User user) {
		Post post = new Post(title, text, tags, user);
		postManager.saveNewPost(post);
		subscriptionsService.notifyNewPost(post);
		return post;
	}
	
	public Post createPost(String title, String text, Set<Tag> tags, User user, Group group) {
		Post post = new Post(title, text, tags, user, group);
		subscriptionsService.notifyNewPost(post);
		postManager.saveNewPost(post);
		return post;
	}
}
