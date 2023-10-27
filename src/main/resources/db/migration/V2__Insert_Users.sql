-- Inserts 2 Users in the User Table, one Admin other normal User
INSERT INTO user (username, password, admin) VALUES ('admin', '$2a$10$vwZ3...asV/b.Ob1Il.e8eiBm.xG95B1RNLp.tVQZdR8KBg1.3ISS', 1);
INSERT INTO user (username, password, admin) VALUES ('user', '$2a$10$vwZ3...asV/b.Ob1Il.e8eiBm.xG95B1RNLp.tVQZdR8KBg1.3ISS', 0);
