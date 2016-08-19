package com.bootcamp.app;

import java.util.HashSet;
import java.util.Set;

public class App {
	
	public static void main(String[] args) {
		
		User matias = new User("matias");
		
		Set<String> matiasTags = new HashSet<>();
		matiasTags.add("Cars");
		matiasTags.add("Science");
		matiasTags.add("Art");
		matias.addTags(matiasTags);
		
		Set<String> post3Tags = new HashSet<>();
		post3Tags.add("Cars");
		post3Tags.add("Smartphones");
		
		Set<String> post8Tags = new HashSet<>();
		post8Tags.add("Books");
		post8Tags.add("Sports");
		post8Tags.add("Science");
		
		matias.createPost("post1", "This is the text of post1", null);
		matias.createPost("post2", "This is the text of post2", null);
		matias.createPost("post3", "This is the text of post3", post3Tags);
		matias.createPost("post4", "This is the text of post4", null);
		matias.createPost("post5", "This is the text of post5", null);
		matias.createPost("post6", "This is the text of post6", null);
		matias.createPost("post7", "This is the text of post7", null);
		matias.createPost("post8", "This is the text of post8", post8Tags);
		matias.createPost("post9", "This is the text of post9", null);
		matias.createPost("post10", "This is the text of post10", null);
		matias.createPost("post11", "This is the text of post11", null);
		
		TagsManager.getInstance().showTags();	
		
		matias.showRecentPosts(10);		
	}
}
