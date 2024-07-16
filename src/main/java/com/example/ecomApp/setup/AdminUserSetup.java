//package com.example.ecomApp.setup;
//
//import com.example.ecomApp.model.Roles;
//import com.example.ecomApp.model.User;
//import com.example.ecomApp.repository.RolesRepository;
//import com.example.ecomApp.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.Collections;
//
//@Component
//public class AdminUserSetup {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RolesRepository rolesRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostConstruct
//    public void init() {
//        // Check if admin role exists, create if not
//        Roles adminRole = rolesRepository.findByRoleName("ADMIN");
//        if (adminRole == null) {
//            adminRole = new Roles();
//            adminRole.setRoleName("ADMIN");
//            rolesRepository.save(adminRole);
//        }
//
//        // Create admin user if not exists
//        if (!userRepository.existsByUserEmail("admin@example.com")) {
//            User adminUser = new User();
//            adminUser.setUserFirstName("Admin");
//            adminUser.setUserLastName("User");
//            adminUser.setUserEmail("admin@example.com");
//            adminUser.setUserPassword(passwordEncoder.encode("adminpassword")); // Encode password
//            adminUser.setUserRole(Collections.singletonList(adminRole)); // Assign admin role
//            adminUser.setEnabled(true); // Enable the admin user
//            userRepository.save(adminUser);
//        }
//    }
//}
