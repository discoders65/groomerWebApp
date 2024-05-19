-- unhashed password for both test accounts: "1234567890!"
INSERT INTO groomer."groomer_user" (id,name, user_name, password, role, email, mobile)
VALUES
    ('6c4f0d63-c89b-4517-a6c1-b71beceec7e3','admin', 'admin', '$2a$10$vRO6QI0nosyiaKcHOOqSau9vy2muONtiBFunmhGuuOBpN4dKSYVhG', 'ADMIN', 'admin@email.com', 999999999),
    ('79f377a0-9d5c-4c17-869a-457600be275e','user', 'user', '$2a$10$vRO6QI0nosyiaKcHOOqSau9vy2muONtiBFunmhGuuOBpN4dKSYVhG', 'USER', 'user@email.com', 111111111);

INSERT INTO groomer."appointment" (id, date_start, date_end, user_id)
VALUES ('46650a5a-e226-463f-a1ae-644c86701e52', '2024-05-20 12:00:00', '2024-05-20 14:00:00', '79f377a0-9d5c-4c17-869a-457600be275e');