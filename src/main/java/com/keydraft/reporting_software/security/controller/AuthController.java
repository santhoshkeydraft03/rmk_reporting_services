package com.keydraft.reporting_software.security.controller;

import com.keydraft.reporting_software.security.dto.AuthRequest;
import com.keydraft.reporting_software.security.dto.AuthResponse;
import com.keydraft.reporting_software.security.dto.RegisterRequest;
import com.keydraft.reporting_software.security.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestHeader(name = "Authorization", required = false) String token) {
        if (token == null || token.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("valid", false);
            response.put("error", "No token provided");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        Map<String, Object> verificationResult = authService.verifyToken(token);
        HttpStatus status = (Boolean) verificationResult.get("valid") ? 
            HttpStatus.OK : HttpStatus.UNAUTHORIZED;
            
        return ResponseEntity.status(status).body(verificationResult);
    }
}