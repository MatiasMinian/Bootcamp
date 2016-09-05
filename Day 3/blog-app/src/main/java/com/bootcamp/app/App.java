package com.bootcamp.app;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bootcamp.app.model.Group;
import com.bootcamp.app.model.Post;
import com.bootcamp.app.model.Tag;
import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.managers.GroupManager;
import com.bootcamp.app.persistence.managers.TagManager;
import com.bootcamp.app.persistence.managers.UserManager;

@SpringBootApplication
public class App {
	
	public static void main(String[] args) {
		
		SpringApplication.run(App.class, args);
		
		/*
		ApplicationContext context = new AnnotationConfigApplicationContext(BlogAppConfig.class);
		
		UserManager userManager = context.getBean(UserManager.class);
		TagManager tagManager = context.getBean(TagManager.class);
		GroupManager groupManager = context.getBean(GroupManager.class);
		
		PostService postService = context.getBean(PostService.class);
		SubscriptionsService subscriptionsService = context.getBean(SubscriptionsService.class);
		
		Tag sports = new Tag("sports");
		Tag books = new Tag("books");
		Tag cars = new Tag("cars");
		tagManager.saveNewTag(books);
		tagManager.saveNewTag(sports);
		tagManager.saveNewTag(cars);
		
		Group utn = new Group("utn");
		groupManager.saveNewGroup(utn);
		
		User matias = new User("matias", "matias@gmail.com");
		User alejo = new User("alejo", "alejo@gmail.com");
		userManager.saveNewUser(matias);
		userManager.saveNewUser(alejo);
		
		Set<Tag> matiasTags = new HashSet<>();
		matiasTags.add(sports);		
		matiasTags.add(books);	
		Set<Tag> alejosTags = new HashSet<>();
		alejosTags.add(cars);
		Post matiasPost = postService.createPost("matiasPost1", "matiasText1", matiasTags, matias, utn);	
		Post alejosPost = postService.createPost("alejosPost1", "alejosText1", alejosTags, alejo);		
		
		postService.addLike(alejo, matiasPost);
		
		subscriptionsService.subscribeToGroup(matias, utn);
		subscriptionsService.subscribeToUser(alejo, matias);
		
		postService.addLike(matias, alejosPost);
		
		((AnnotationConfigApplicationContext)context).close();
		*/
	}
}
