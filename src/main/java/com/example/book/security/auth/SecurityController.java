package com.example.book.security.auth;

import com.example.book.security.auth.dto.AuthLoginDTO;
import com.example.book.security.auth.dto.AuthRegisterDTO;
import com.example.book.security.auth.dto.AuthResponseDTO;
import com.example.book.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SecurityController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody AuthRegisterDTO registerDTO){
        AuthResponseDTO responseDTO = userService.register(registerDTO);
        return ResponseEntity.ofNullable(responseDTO);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthLoginDTO loginDTO){
        AuthResponseDTO responseDTO = userService.login(loginDTO);
        return ResponseEntity.ofNullable(responseDTO);
    }


}
