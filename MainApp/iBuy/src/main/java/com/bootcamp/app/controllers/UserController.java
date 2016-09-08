package com.bootcamp.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.app.model.User;
import com.bootcamp.app.model.responses.SimpleUserResponse;
import com.bootcamp.app.persistence.managers.UserManager;

@RestController
public class UserController {
	
	@Autowired
	UserManager userManager;
	
	public UserController() {
	}
	
	public UserController(UserManager userManager) {
		this.userManager = userManager;
	}
	
	@RequestMapping(value = "/api/users", produces = "application/json", method = RequestMethod.GET)
	public List<SimpleUserResponse> getUsers() {
		List<User> users = userManager.findAllUsers();	
		List<SimpleUserResponse> usersResponse = new ArrayList<>();
		users.forEach(user -> usersResponse.add(new SimpleUserResponse(user)));
		return usersResponse;
	}
}