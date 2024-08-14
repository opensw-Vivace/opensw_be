package com.vivace.opensw.controller;


import com.vivace.opensw.dto.member.request.LoginRequestDto;
import com.vivace.opensw.dto.member.request.SignUpRequestDto;
import com.vivace.opensw.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    @Value("${jwt.header}")
    private String jwtHeader;

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestDto requestDto){
        authService.signUp(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto requestDto){
        String accessToken = authService.login(requestDto);
        HttpHeaders headers = new HttpHeaders();
        headers.set(jwtHeader, "Bearer " + accessToken);
        return ResponseEntity.ok().headers(headers).build();
    }
}
