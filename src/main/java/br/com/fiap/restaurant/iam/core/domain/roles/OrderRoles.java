package br.com.fiap.restaurant.iam.core.domain.roles;

public enum OrderRoles implements ForGettingRoleName {
    VIEW_ORDER, VIEW_ALL_ORDER, CANCEL_ORDER, REVERSE_ORDER;

    @Override
    public String getRoleName() {
        return name();
    }
}
