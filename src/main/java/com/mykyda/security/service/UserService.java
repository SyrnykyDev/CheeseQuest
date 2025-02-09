package com.mykyda.security.service;

import com.mykyda.security.database.entity.Role;
import com.mykyda.security.database.entity.User;
import com.mykyda.security.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User update(User user) {
        return user;
    }


    public ResponseEntity<?> register(String username, String email, String password) {
        if (userRepository.findByEmail(email).isEmpty()) {
            var user = User.builder()
                    .role(Role.USER)
                    .username(username)
                    .email(email)
                    .avatar("none.png")
                    .password(passwordEncoder.encode(password))
                    .build();
            userRepository.save(user);
            return new ResponseEntity<>(Collections.singletonMap("message", "Користувач успішно зареєстрований"), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("error", "Email вже використовується"), HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> register(String email, String password, Role role) {
        if (userRepository.findByEmail(email).isEmpty()) {
            var user = User.builder()
                    .role(role)
                    .username("user" + System.currentTimeMillis() * email.hashCode() / 100000)
                    .email(email)
                    .avatar("none.png")
                    .password(passwordEncoder.encode(password))
                    .build();
            userRepository.save(user);
            return new ResponseEntity<>(Collections.singletonMap("message", "Користувач успішно зареєстрований"), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("error", "Email вже використовується"), HttpStatus.CONFLICT);
        }
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

    public ResponseEntity<User> findById(Long id) {
        var optUser = userRepository.findById(id);
        return optUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
