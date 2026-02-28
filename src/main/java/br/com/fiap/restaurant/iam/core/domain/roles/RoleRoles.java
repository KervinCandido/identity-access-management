package br.com.fiap.restaurant.iam.core.domain.roles;

public enum RoleRoles implements ForGettingRoleName {
    VIEW_ROLE;

    @Override
    public String getRoleName() {
        return name();
    }
}
