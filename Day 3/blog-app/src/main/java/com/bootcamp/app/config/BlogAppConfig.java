package com.bootcamp.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.bootcamp.app.Mailer;
import com.bootcamp.app.PostService;
import com.bootcamp.app.SubscriptionsService;
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
@EnableAspectJAutoProxy
public class BlogAppConfig {
	
	@Bean(name = "myGroupDAO")
	public GroupDAO groupDAO() {
		return new GroupDAO(Group.class);
	}
	
	@Bean(name = "myUserDAO")
	public UserDAO userDAO() {
		return new UserDAO(User.class);
	}
	
	@Bean(name = "myPostDAO")
	public PostDAO postDAO() {
		return new PostDAO(Post.class);
	}
	
	@Bean(name = "myTagDAO")
	public TagDAO tagDAO() {
		return new TagDAO(Tag.class);
	}
	
	@Bean(name = "myGroupManager")
	public GroupManager groupManager() {
		return new GroupManager(groupDAO());
	}
	
	@Bean(name = "myUserManager")
	public UserManager userManager() {
		return new UserManager(userDAO());
	}
	
	@Bean(name = "myPostManager")
	public PostManager postManager() {
		return new PostManager(postDAO());
	}
	
	@Bean(name = "myTagManager")
	public TagManager tagManager() {
		return new TagManager(tagDAO());
	}
	
	@Bean(name = "myMailer")
	public Mailer Mailer() {
		return new Mailer();
	}
	
	@Bean(name = "mySubscriptionsService")
	public SubscriptionsService subscriptionsService() {
		return new SubscriptionsService(userManager(), groupManager(), Mailer());		
	}
	
	@Bean(name = "myPostService")
	public PostService postService() {
		return new PostService(postManager(), subscriptionsService());
	}
}