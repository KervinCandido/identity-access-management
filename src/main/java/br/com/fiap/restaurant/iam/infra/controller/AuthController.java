package br.com.fiap.restaurant.iam.infra.controller;

import br.com.fiap.restaurant.iam.infra.controller.request.AuthRequest;
import br.com.fiap.restaurant.iam.infra.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid AuthRequest authRequest) {
        return  ResponseEntity.ok(authenticationService.authentication(authRequest));
    }
}
