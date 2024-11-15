package org.alexgiou.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.alexgiou.ecommerce.entity.user.User;
import org.alexgiou.ecommerce.request.RegisterRequest;
import org.alexgiou.ecommerce.service.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/23/2024
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {

        authService.register(registerRequest);
        return ResponseEntity.ok("User registered successfully");
    }
}
