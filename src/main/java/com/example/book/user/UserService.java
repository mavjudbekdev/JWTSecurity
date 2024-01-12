package com.example.book.user;

import com.example.book.security.auth.dto.AuthLoginDTO;
import com.example.book.security.auth.dto.AuthRegisterDTO;
import com.example.book.security.auth.dto.AuthResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with phone number: %s not found", username)));
    }

    public AuthResponseDTO register(AuthRegisterDTO registerDTO) {
        return null;
    }

    public AuthResponseDTO login(AuthLoginDTO loginDTO) {
        return null;
    }
}
