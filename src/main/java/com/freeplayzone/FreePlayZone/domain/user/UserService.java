package com.freeplayzone.FreePlayZone.domain.user;

import com.freeplayzone.FreePlayZone.dto.*;
import com.freeplayzone.FreePlayZone.infra.security.TokenService;
import com.freeplayzone.FreePlayZone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public loginResponseDTO register(RegisterRequestDTO body)
    {
        if (userRepository.findByEmail(body.email()).isPresent()) {
            throw new IllegalArgumentException("Email já registrado.");
        }
        User newUser = new User();
        newUser.setEmail(body.email());
        newUser.setName(body.name());
        newUser.setPassword(passwordEncoder.encode(body.password()));
        userRepository.save(newUser);

        String token = tokenService.generateToken(newUser);
        return new loginResponseDTO(newUser.getUsername(), token);
    }

    public loginResponseDTO login(LoginRequestDTO body)
    {
        User user = userRepository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            throw new IllegalArgumentException("Senha inválida.");
        }
        String token = tokenService.generateToken(user);
        return new loginResponseDTO(user.getUsername(), token);
    }

    public void updateUser(UpdateRequestDTO body)
    {
        User user = userRepository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        user.setName(body.newName());
        user.setPassword(passwordEncoder.encode(body.newPassword()));
        userRepository.save(user);
    }
}
