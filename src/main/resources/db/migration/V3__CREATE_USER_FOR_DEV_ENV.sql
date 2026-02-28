INSERT INTO users (id, name, user_name, email, password_hash, user_type_id)
VALUES (
           '11111111-1111-1111-1111-111111111111',
           'dev',
        'dev',
           'dev.ownerId@mail.com',
           '$2a$10$7QVD8Ww9CX9sNQfTTk1W0e7ChvMo9vaEtupQ6YNhZ65AIwmuweOsu', -- dev
           (SELECT id FROM user_types WHERE name = 'RESTAURANT_OWNER')
       );
