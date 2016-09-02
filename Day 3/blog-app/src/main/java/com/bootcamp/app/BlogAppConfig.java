package com.bootcamp.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bootcamp.app.model.Group;
import com.bootcamp.app.model.Post;
import com.bootcamp.app.model.Tag;
import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.daos.GroupDAO;
import com.bootcamp.app.persistence.daos.PostDAO;
import com.bootcamp.app.persistence.daos.TagDAO;
import com.bootcamp.app.persistence.daos.UserDAO;
import com.bootcamp.app.persistence.managers.GroupManager;
import com.bootcamp.app.persistence.managers.PostManager;
import com.bootcamp.app.persistence.managers.TagManager;
import com.bootcamp.app.persistence.managers.UserManager;

@Configuration
public class BlogAppConfig {
	
	@Bean
	public GroupDAO groupDAO() {
		return new GroupDAO(Group.class);
	}
	
	@Bean
	public UserDAO userDAO() {
		return new UserDAO(User.class);
	}
	
	@Bean
	public PostDAO postDAO() {
		return new PostDAO(Post.class);
	}
	
	@Bean
	public TagDAO tagDAO() {
		return new TagDAO(Tag.class);
	}
	
	@Bean
	public GroupManager groupManager() {
		return new GroupManager(groupDAO());
	}
	
	@Bean
	public UserManager userManager() {
		return new UserManager(userDAO());
	}
	
	@Bean
	public PostManager postManager() {
		return new PostManager(postDAO());
	}
	
	@Bean
	public TagManager tagManager() {
		return new TagManager(tagDAO());
	}
	
	@Bean
	public SubscriptionsService subscriptionsService() {
		return new SubscriptionsService(userManager(), groupManager());		
	}
	
	@Bean
	public PostService postService() {
		return new PostService(postManager(), subscriptionsService());
	}
}