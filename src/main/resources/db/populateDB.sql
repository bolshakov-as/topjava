DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id) VALUES
  ('2016-03-25 11:00:00','Zavtrak', 500, 100000),
  ('2016-03-25 13:00:00','Obed', 1000, 100000),
  ('2016-03-25 17:00:00','Uzhin', 600, 100000),
  ('2016-03-26 11:00:00','Zavtrak', 500, 100000),
  ('2016-03-26 13:00:00','Obed', 1000, 100000),
  ('2016-03-26 17:00:00','Uzhin', 400, 100000),
  ('2016-03-26 13:00:00','Obed', 1000, 100001),
  ('2016-03-26 17:00:00','Uzhin', 400, 100001)

