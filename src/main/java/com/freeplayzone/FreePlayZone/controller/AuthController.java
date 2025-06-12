package com.freeplayzone.FreePlayZone.controller;

import com.freeplayzone.FreePlayZone.dto.*;
import com.freeplayzone.FreePlayZone.domain.user.User;
import com.freeplayzone.FreePlayZone.repository.UserRepository;
import com.freeplayzone.FreePlayZone.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body)
    {
        Optional<User> user = this.userRepository.findByEmail(body.email());
        if(user.isEmpty())
        {
            User newUser =new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            this.userRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getUsername(),token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body)
    {
        User user = this.userRepository.findByEmail(body.email()).orElseThrow(() ->
                new RuntimeException("user not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword()))
        {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getUsername(),token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody UpdateRequestDTO body) {
        Optional<User> user = this.userRepository.findByEmail(body.email());

        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setPassword(passwordEncoder.encode(body.newPassword()));
            existingUser.setName(body.newName());

            this.userRepository.save(existingUser);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
