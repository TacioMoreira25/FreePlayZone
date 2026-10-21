package com.freeplayzone.FreePlayZone.infra.security;

import com.freeplayzone.FreePlayZone.domain.user.User;
import com.freeplayzone.FreePlayZone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserdetailsService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = this.userRepository.findByEmail
                (username).orElseThrow
                (() -> new UsernameNotFoundException("Usuário não encontrando!"));
        return org.springframework.security.core.userdetails.User
                .builder().username(user.getEmail())
                .password(user.getPassword()).roles("USER")
                .build();
    }
}