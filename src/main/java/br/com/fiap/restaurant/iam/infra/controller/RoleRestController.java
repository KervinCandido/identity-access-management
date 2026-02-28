package br.com.fiap.restaurant.iam.infra.controller;

import br.com.fiap.restaurant.iam.core.controller.RoleController;
import br.com.fiap.restaurant.iam.core.outbound.RoleOutput;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleRestController {

    private final RoleController roleController;

    public RoleRestController(RoleController roleController) {
        this.roleController = roleController;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllRoles() {
        return roleController.getAllRoles().stream().map(RoleOutput::name).toList();
    }
}
