package com.bootcamp.app.services;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.bootcamp.app.model.User;
import com.bootcamp.app.utils.DatesUtil;

@Service
public class UserService {
	
	public UserService() {}
	
	public boolean isDeleteable(User user) {
		return DatesUtil.differenceInDays(user.getLastLogin(), Calendar.getInstance()) > 30;
	}
}
