package com.bootcamp.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
	
	/* *** GETTERS & SETTERS *** */
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
