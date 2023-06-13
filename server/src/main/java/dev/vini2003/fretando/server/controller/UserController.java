package dev.vini2003.fretando.server.controller;

import dev.vini2003.fretando.common.entity.User;
import dev.vini2003.fretando.server.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity<User> user() {
        return new ResponseEntity<>(HttpStatus.LOOP_DETECTED);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        // Check if user already exists
        var existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        // Encode password
        var passwordEncoder = new BCryptPasswordEncoder();
        var encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        // Save new user
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
