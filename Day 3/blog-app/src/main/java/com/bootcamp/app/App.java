package com.bootcamp.app;

import com.bootcamp.app.model.Group;
import com.bootcamp.app.model.Post;
import com.bootcamp.app.model.Tag;
import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.managers.GroupManager;
import com.bootcamp.app.persistence.managers.PostManager;
import com.bootcamp.app.persistence.managers.TagManager;
import com.bootcamp.app.persistence.managers.UserManager;

public class App {
	
	public static void main(String[] args) {
		
		UserManager userManager = new UserManager();
		TagManager tagManager = new TagManager();
		GroupManager groupManager = new GroupManager();
		PostManager postManager = new PostManager();
		
		PostService postService = new PostService();
		SubscriptionsService subscriptionsService = new SubscriptionsService();
		
		User matias = new User("matias", "matias@gmail.com");
		User alejo = new User("alejo", "alejo@gmail.com");
		
		Group utn = new Group("utn");
		
		Tag sports = new Tag("sports");
		Tag books = new Tag("books");
		Tag cars = new Tag("cars");
		
		Post matiasPost = postService.createPost("matiasPost1", "matiasText1", null, matias, utn);	
		matiasPost.getTags().add(sports);
		matiasPost.getTags().add(books);
		Post alejosPost = postService.createPost("alejosPost1", "alejosText1", null, alejo);
		alejosPost.getTags().add(cars);
		
		postService.addLike(alejo, matiasPost);
		
		subscriptionsService.subscribeToGroup(matias, utn);
		subscriptionsService.subscribeToUser(alejo, matias);
		
		tagManager.saveNewTag(books);
		tagManager.saveNewTag(sports);
		tagManager.saveNewTag(cars);
		
		groupManager.saveNewGroup(utn);

		userManager.saveNewUser(matias);
		userManager.saveNewUser(alejo);

		postManager.saveNewPost(matiasPost);
		postManager.saveNewPost(alejosPost);
		
		postService.addLike(matias, alejosPost);
		postManager.updatePost(alejosPost);		
	}
}
