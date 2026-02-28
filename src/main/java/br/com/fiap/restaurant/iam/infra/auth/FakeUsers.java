package br.com.fiap.restaurant.iam.infra.auth;

import br.com.fiap.restaurant.iam.core.domain.model.*;
import br.com.fiap.restaurant.iam.core.domain.roles.*;
import org.reflections.Reflections;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class FakeUsers {

    private static final String ROLES_PACKAGE = "br.com.fiap.restaurant.iam.core.domain.roles";

    private static final String PASSWORD_HASH = "{fake}dev-password-hash";

    private static final UUID DEV_OWNER_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");

    private FakeUsers() {}

    public static User devAdminOwnerUser() {
        Set<Role> roles = new HashSet<>();
        Reflections reflections = new Reflections(ROLES_PACKAGE);
        Set<Class<? extends ForGettingRoleName>> roleClasses = reflections.getSubTypesOf(ForGettingRoleName.class);
        var rolesFakeId = new AtomicLong(1);
        for (Class<? extends ForGettingRoleName> clazz : roleClasses) {
            if (clazz.isEnum()) {
                roles.addAll(Stream.of(clazz.getEnumConstants())
                        .map(e -> new Role(rolesFakeId.getAndIncrement(), e.getRoleName()))
                        .toList());
            }
        }

        UserType type = new UserType(1L, "Dono de Restaurante", roles);

        return new User(
                DEV_OWNER_ID,
                "Dev Owner",
                "dev",
                "dev.ownerId@dev.local",
                null,
                type,
                PASSWORD_HASH
        );
    }
}
