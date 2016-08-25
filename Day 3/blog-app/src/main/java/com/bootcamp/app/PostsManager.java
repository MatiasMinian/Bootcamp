package com.bootcamp.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PostsManager {
	
	private static PostsManager postsManager = null;
	
	private List<Post> posts = new ArrayList<>();
	private HashMap<String, List<Post>> groupPosts = new HashMap<>();
	
	public static PostsManager getInstance() {
		if (postsManager == null) {
			postsManager = new PostsManager();
		}
		return postsManager;
	}
	
	public List<Post> getPostsOfGroup(String groupName) {
		return groupPosts.get(groupName);
	}
	
	public void deletePostsOfGroup(String groupName) {
		groupPosts.remove(groupName);
	}
	
	public void deletePost(Post post) {
		posts.remove(post);
	}
	
	public List<Post> sortByNewest(int quantity, boolean reverse) {
		// quantity == 0 return all posts
		posts.sort(new Comparator<Post>() {

			@Override
			public int compare(Post post1, Post post2) {
				if (post1.getDate().before(post2.getDate())) {
					return 1;
				} else if (post1.getDate().after(post2.getDate())) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		if (reverse) {
			Collections.reverse(posts);
		}
		if (quantity == 0) {
			return posts;
		}
		return posts.subList(0, quantity);
	}
	
	public List<Post> sortAlphabeticallyByTitle(int quantity, boolean reverse) {
		// quantity == 0 return all posts
		posts.sort((post1, post2) -> post1.getTitle().compareTo(post2.getTitle()));
		if (reverse) {
			Collections.reverse(posts);
		}
		if (quantity == 0) {
			return posts;
		}
		return posts.subList(0, quantity);	
	}
	
	public List<Post> sortByLikes(int quantity, boolean reverse) {
		posts.sort((post1, post2) -> post2.getLikes().size() - post1.getLikes().size());
		if (reverse) {
			Collections.reverse(posts);
		}
		if (quantity == 0) {
			return posts;
		}
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
	
	public void addPost(Post post) {
		posts.add(post);
	}
	
	public void addGroupPost(Post post, String groupName) {
		List<Post> posts = groupPosts.get(groupName);
		if (posts == null) {
			posts = new ArrayList<>();
			posts.add(post);
			groupPosts.put(groupName, posts);
		} else {
			posts.add(post);
		}
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public HashMap<String, List<Post>> getGroupPosts() {
		return groupPosts;
	}

	public void setGroupPosts(HashMap<String, List<Post>> groupPosts) {
		this.groupPosts = groupPosts;
	}
}
