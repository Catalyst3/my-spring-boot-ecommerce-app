package com.example.ecomApp.service;


import com.example.ecomApp.model.User;

public interface IUserService {

	public void registerUser(User user);
	
	public boolean isUserAlreadyPresent(String userEmail);
}
