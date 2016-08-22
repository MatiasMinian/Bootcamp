package com.bootcamp.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PostsManager {
	
	private static PostsManager postsManager = null;
	
	private List<Post> posts = new ArrayList<>();
	
	public static PostsManager getInstance() {
		if (postsManager == null) {
			postsManager = new PostsManager();
		}
		return postsManager;
	}
	
	public Post createPost(String title, String text, Set<String> tags, User user) {
		Post post = new Post(title, text, tags, user);
		posts.add(0, post);	
		return post;
	}
	
	public void deletePost(Post post) {
		posts.remove(post);
	}
	
	public List<Post> recentPosts(int quantity) {
		return posts.subList(0, quantity);
	}
	
	public List<Post> searchByTag(String tag) {
		return posts.stream().filter(post -> post.getTags().contains(tag)).collect(Collectors.toList());
	}
	
	public List<Post> searchByText(String text) {
		return posts.stream().filter(post -> post.getText().contains(text)).collect(Collectors.toList());
	}
	
	public List<Post> searchByUser(User user) {
		return posts.stream().filter(post -> post.getUser() == user).collect(Collectors.toList());
	}
	
	public List<Post> searchByDate(Calendar dateFrom, Calendar dateTo) {
		return posts.stream().filter(post -> post.getDate().after(dateFrom) && post.getDate().before(dateTo)).collect(Collectors.toList());
	}
	
	/* *** GETTERS & SETTERS *** */
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
