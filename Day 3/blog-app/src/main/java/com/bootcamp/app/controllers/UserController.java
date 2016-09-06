package com.bootcamp.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.daos.UserDAO;
import com.bootcamp.app.persistence.managers.UserManager;
import com.mysql.jdbc.MiniAdmin;

@RestController
public class UserController {
	
	@Autowired
	UserManager userManager;
	
	public UserController(){}
	
	public UserController(UserManager userManager) {
		this.userManager = userManager;
	}
	
	@RequestMapping(value = "/api", produces = "application/json")
	public User userLogin(@RequestParam(value = "email", defaultValue = "un email") String email, @RequestParam(value = "password", defaultValue = "un pass") String pass) {
		User user = new User(email, pass);
		userManager.saveNewUser(user);
		return user;
	}
	
	@RequestMapping("/api/user/{id}")
	public User getUserById(@PathVariable Long id) {
		User user = (new UserManager(new UserDAO(User.class))).findUserById(id);
		return user;		
	}
	
	@RequestMapping("/api/users")
	public List<User> getUsers() {
		User matias = new User("matias", "minian.matias@gmail.com");
		User alejo = new User("alejo", "alejo@gmail.com");
		List<User> users = new ArrayList<>();
		users.add(matias);
		users.add(alejo);
		return users;
	}
}
