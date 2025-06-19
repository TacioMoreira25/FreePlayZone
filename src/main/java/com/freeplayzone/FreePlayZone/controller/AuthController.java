package com.freeplayzone.FreePlayZone.controller;

import com.freeplayzone.FreePlayZone.domain.user.UserService;
import com.freeplayzone.FreePlayZone.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO body)
    {
        try {
            var response = userService.register(body);
            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body)
    {
        try {
            var response = userService.login(body);
            return ResponseEntity.ok(response);
        }
        catch (RuntimeException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody UpdateRequestDTO body)
    {
        try {
            userService.updateUser(body);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e)
        {
            return ResponseEntity.notFound().build();
        }
    }
}
