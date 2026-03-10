package br.com.fiap.restaurant.iam.infra.service;

import br.com.fiap.restaurant.iam.core.exception.InvalidCredentialsException;
import br.com.fiap.restaurant.iam.infra.controller.request.AuthRequest;
import br.com.fiap.restaurant.iam.infra.persistence.repository.UserRepository;
import br.com.fiap.restaurant.iam.infra.dto.JwtBearerToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public JwtBearerToken authentication(AuthRequest authRequest) throws InvalidCredentialsException {
        var user = userRepository.findByUsername(authRequest.username())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(authRequest.password(), user.getPassword()))
            throw new InvalidCredentialsException();

        return jwtService.generateToken(user);
    }
}
