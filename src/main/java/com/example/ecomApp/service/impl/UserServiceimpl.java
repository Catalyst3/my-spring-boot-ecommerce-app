package com.example.ecomApp.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecomApp.model.Roles;
import com.example.ecomApp.model.User;
import com.example.ecomApp.repository.RolesRepository;
import com.example.ecomApp.repository.UserRepository;
import com.example.ecomApp.service.IUserService;

@Service
public class UserServiceimpl implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RolesRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder; // Inject Spring Security PasswordEncoder

//	@Override
//	public void registerUser(User user) {
//		System.out.println("Inside The register Method");
//		user.setEnabled(true); // Set user as enabled
//		user.setUserPassword(passwordEncoder.encode(user.getUserPassword())); // Encode password
//		userRepository.save(user);
//	}
	@Transactional
    public void registerUser(User user) {
        System.out.println("Inside The register Method");

        // Set user as enabled
        user.setEnabled(true);
        System.out.println("setEnabled");

        // Encode user password
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        System.out.println("setUserPassword");
        
        // Set user role (ensure "USER" role exists in database)
        Roles userRole = roleRepository.findByRoleName("USER");
        System.out.println("findByRoleName"+userRole);
        if (userRole == null) {
        	System.out.println("INside IF Not Found");
            // If "USER" role doesn't exist, create and save it
            userRole = new Roles();
            userRole.setRoleName("USER");
            roleRepository.save(userRole);
        }

        // Assign "USER" role to the user
        System.out.println("Get User "+user.getUserRole());
        user.getUserRole().add(userRole);

        // Save the user entity with assigned roles
        userRepository.save(user);
    }

	@Override
	public boolean isUserAlreadyPresent(String userEmail) {
		Optional<User> existingUser = userRepository.findByUserEmail(userEmail);
		return existingUser.isPresent();
	}

}
