INSERT INTO roles (name) VALUES ('VIEW_ORDER');
INSERT INTO roles (name) VALUES ('VIEW_ALL_ORDER');
INSERT INTO roles (name) VALUES ('CANCEL_ORDER');
INSERT INTO roles (name) VALUES ('REVERSE_ORDER');

INSERT INTO user_types_roles (user_type_id, role_id)
SELECT ut.id, r.id FROM user_types ut
    JOIN roles r ON 1=1
WHERE ut.name = 'RESTAURANT_OWNER' AND r.name IN ('VIEW_ORDER', 'VIEW_ALL_ORDER', 'CANCEL_ORDER', 'REVERSE_ORDER');
