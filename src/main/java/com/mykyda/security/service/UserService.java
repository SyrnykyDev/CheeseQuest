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
import java.util.Map;
import java.util.Objects;

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
                    .avatar("https://media.istockphoto.com/id/587805156/uk/%D0%B2%D0%B5%D0%BA%D1%82%D0%BE%D1%80%D0%BD%D1%96-%D0%B7%D0%BE%D0%B1%D1%80%D0%B0%D0%B6%D0%B5%D0%BD%D0%BD%D1%8F/%D0%B2%D0%B5%D0%BA%D1%82%D0%BE%D1%80%D0%BD%D0%B0-%D1%96%D0%BB%D1%8E%D1%81%D1%82%D1%80%D0%B0%D1%86%D1%96%D1%8F-%D0%B7%D0%BE%D0%B1%D1%80%D0%B0%D0%B6%D0%B5%D0%BD%D0%BD%D1%8F-%D0%BF%D1%80%D0%BE%D1%84%D1%96%D0%BB%D1%8E.jpg?s=2048x2048&w=is&k=20&c=KiYHL-WRDFc2zSv16pNkgHOEIBspZM0jIFVA7qZsOzI=")
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
                    .avatar("https://media.istockphoto.com/id/587805156/uk/%D0%B2%D0%B5%D0%BA%D1%82%D0%BE%D1%80%D0%BD%D1%96-%D0%B7%D0%BE%D0%B1%D1%80%D0%B0%D0%B6%D0%B5%D0%BD%D0%BD%D1%8F/%D0%B2%D0%B5%D0%BA%D1%82%D0%BE%D1%80%D0%BD%D0%B0-%D1%96%D0%BB%D1%8E%D1%81%D1%82%D1%80%D0%B0%D1%86%D1%96%D1%8F-%D0%B7%D0%BE%D0%B1%D1%80%D0%B0%D0%B6%D0%B5%D0%BD%D0%BD%D1%8F-%D0%BF%D1%80%D0%BE%D1%84%D1%96%D0%BB%D1%8E.jpg?s=2048x2048&w=is&k=20&c=KiYHL-WRDFc2zSv16pNkgHOEIBspZM0jIFVA7qZsOzI=")
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
        var result = userRepository.findByEmail(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                )).orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve username " + username));
        System.out.println(result);
        return result;
    }

    public ResponseEntity<?> findById(Long id) {
        var optUser = userRepository.findById(id);
        return optUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> save(Map<String, Object> claims) {
        var user = User.builder()
                .role(Role.USER)
                .email(claims.get("email").toString())
                .avatar(claims.get("picture").toString())
                .username(claims.get("name").toString())
                .password(passwordEncoder.encode(String.valueOf(System.currentTimeMillis()*claims.hashCode())))
                .build();
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
