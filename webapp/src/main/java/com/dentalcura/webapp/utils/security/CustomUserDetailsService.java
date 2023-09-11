//package com.dentalcura.webapp.utils.security;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    // Inject your data repository (e.g., UserRepository) here
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Load user details from your data repository
//        // Here, you fetch a user entity from your data source
//
//        // Example: UserEntity userEntity = userRepository.findByUsername(username);
//
//        if (userEntity == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//
//        // Map roles to GrantedAuthority objects
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (UserRoles role : userEntity.getRoles()) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
//        }
//
//        // Create and return a UserDetails object
//        return new User(
//                userEntity.getUsername(),
//                userEntity.getPassword(),
//                authorities
//        );
//    }
//}
//
