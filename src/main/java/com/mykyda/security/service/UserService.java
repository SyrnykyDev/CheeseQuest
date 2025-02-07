package com.mykyda.security.service;

import com.mykyda.security.database.entity.Role;
import com.mykyda.security.database.entity.User;
import com.mykyda.security.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User update(User user) {
        return user;
    }

    public User reg(String email, String password, Role role) {
        return userRepository.save(User.builder()
                .role(role)
                .username("User" + email.hashCode() * System.currentTimeMillis() / 1000)
                .email(email)
                .avatar("none.png")
                .password(passwordEncoder.encode(password)).build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                )).orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve username " + username));
    }

    public User findById(Long id) {
        var optUser = userRepository.findById(id);
        return optUser.orElse(null);
    }

}
