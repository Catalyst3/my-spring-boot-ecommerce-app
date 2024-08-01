package com.example.ecomApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ecomApp.model.Roles;
import com.example.ecomApp.model.User;
import com.example.ecomApp.repository.UserRepository;
import com.example.ecomApp.service.IUserService;

@Controller
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	IUserService iUserService;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/register")
	public String showRegistrationForm() {
		return "register";
	}

	@PostMapping("/register")
	public String registerUserAccount(@ModelAttribute("user") @Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return "register";
		}
		if (iUserService.isUserAlreadyPresent(user.getUserEmail())) {
			// Add custom error for duplicate email
			result.rejectValue("userEmail", "error.user", "There is already a user registered with this email.");
			return "register";
		}

		// Save new User to Database
		iUserService.registerUser(user);

		// Auto login the user after successful registration
		authenticateUserAndSetSession(user);

		// Redirect to a protected page
		return "redirect:/home";
	}

	private void authenticateUserAndSetSession(User user) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUserEmail(),
				user.getUserPassword());
		SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authToken));
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

}
