package com.example.ecomApp.security;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ecomApp.model.User;
import com.example.ecomApp.repository.UserRepository;
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads a user by their email address.
     *
     * @param userEmail the email address of the user to be loaded.
     * @return UserDetails representing the user.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        System.out.println("Starting to find user with email: " + userEmail);

        // Retrieve the user by email from the repository
        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);

        // Throw an exception if the user is not found
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));

        // Log the retrieved user
        User user = optionalUser.get();
        System.out.println("Found user: " + user);

        // Map the User entity to CustomUserDetails and return it
        System.out.println("Creating UserDetails for user: " + user);
        return optionalUser.map(CustomUserDetails::new).get();
    }

}
