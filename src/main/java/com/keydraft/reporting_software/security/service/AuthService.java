package com.keydraft.reporting_software.security.service;

import com.keydraft.reporting_software.security.dto.AuthRequest;
import com.keydraft.reporting_software.security.dto.AuthResponse;
import com.keydraft.reporting_software.security.dto.RegisterRequest;
import com.keydraft.reporting_software.security.entity.User;
import com.keydraft.reporting_software.security.jwt.JwtService;
import com.keydraft.reporting_software.security.repository.UserRepository;
import com.keydraft.reporting_software.security.entity.Role;
import com.keydraft.reporting_software.security.repository.RoleRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;
        private final RoleRepository roleRepository;
        
        public AuthService(UserRepository userRepository, 
                         PasswordEncoder passwordEncoder,
                         JwtService jwtService,
                         AuthenticationManager authenticationManager,
                         RoleRepository roleRepository) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
            this.jwtService = jwtService;
            this.authenticationManager = authenticationManager;
            this.roleRepository = roleRepository;
        }

        public AuthResponse register(RegisterRequest request) {
            String roleName = request.getRole() != null ? request.getRole() : "USER";
            
            Role role = roleRepository.findByRoleName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));

            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(role);

            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);

            AuthResponse response = new AuthResponse();
            response.setToken(jwtToken);
            response.setUsername(user.getUsername());
            return response;
        }

        public AuthResponse authenticate(AuthRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUsername(),
                                                request.getPassword()));

                var user = userRepository.findByUsername(request.getUsername())
                                .orElseThrow();

                var jwtToken = jwtService.generateToken(user);

                AuthResponse response = new AuthResponse();
                response.setToken(jwtToken);
                response.setUsername(user.getUsername());
                return response;
        }

        public Map<String, Object> verifyToken(String token) {
            return jwtService.verifyToken(token);
        }
}