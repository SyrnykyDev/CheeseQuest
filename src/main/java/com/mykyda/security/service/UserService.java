package com.mykyda.security.service;

import com.mykyda.api.dto.ProfileEditDto;
import com.mykyda.api.dto.UserDemoDto;
import com.mykyda.api.service.MediaService;
import com.mykyda.security.database.entity.Role;
import com.mykyda.security.database.entity.User;
import com.mykyda.security.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final MediaService mediaService;

    final String defaultAvatar = "https://userprofilepicturesbucket.s3.eu-north-1.amazonaws.com/images/00cad4aa-db93-4920-aab6-3bc341b0fb87user.png";

    public User update(User user) {
        return user;
    }


    public User register(String username, String email, String password) {
        if (userRepository.findByEmail(email).isEmpty()) {
            var user = User.builder()
                    .role(Role.USER)
                    .username(username)
                    .email(email)
                    .avatar(defaultAvatar)
                    .password(passwordEncoder.encode(password))
                    .build();
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public ResponseEntity<?> register(String email, String password, Role role) {
        if (userRepository.findByEmail(email).isEmpty()) {
            var user = User.builder()
                    .role(role)
                    .username("user" + System.currentTimeMillis() * email.hashCode() / 100000)
                    .email(email)
                    .avatar(defaultAvatar)
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
        var user = userRepository.findById(id).get();
        if (user == null) {
            return new ResponseEntity<>(Collections.singletonMap("message", "such user don`t exist"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new UserDemoDto(user.getAvatar()
                    , user.getUsername()
                    , user.getEmail()
                    , user.getRole()), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> create(Map<String, Object> claims) {
        var user = User.builder()
                .role(Role.USER)
                .email(claims.get("email").toString())
                .avatar(claims.get("picture").toString())
                .username(claims.get("name").toString())
                .password(passwordEncoder.encode(String.valueOf(System.currentTimeMillis() * claims.hashCode())))
                .build();
        return new ResponseEntity<>(Collections.singletonMap("message", "user successfully created"), HttpStatus.CREATED);
    }

    public ResponseEntity<?> save(MultipartFile file, String username, Principal principal) {
        var user = userRepository.findByEmail(principal.getName()).get();
        if (file!=null){
            if (username!=null){
                user.setUsername(username);
            }
            user.setAvatar(mediaService.uploadProfileImage(file));
            userRepository.save(user);
        } else {
            if (username!=null){
                user.setUsername(username);
                userRepository.save(user);
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("message", "user successfully updated"), HttpStatus.OK);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public ResponseEntity<?> findByPrincipal(Principal principal) {
        var user = userRepository.findByEmail(principal.getName()).get();
        if (user == null) {
            return new ResponseEntity<>(Collections.singletonMap("message", "such user don`t exist"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new UserDemoDto(user.getAvatar()
                    , user.getUsername()
                    , user.getEmail()
                    , user.getRole()), HttpStatus.OK);
        }
    }
}
