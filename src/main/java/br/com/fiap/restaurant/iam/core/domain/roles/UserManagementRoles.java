package br.com.fiap.restaurant.iam.core.domain.roles;

public enum UserManagementRoles implements ForGettingRoleName {
    CREATE_USER,
    UPDATE_USER,
    DELETE_USER,
    VIEW_USER;

    @Override
    public String getRoleName() {
        return name();
    }
}
