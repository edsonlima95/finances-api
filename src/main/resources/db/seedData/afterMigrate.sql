--delete users;
--delete categories;
--delete wallets;

INSERT INTO users (name, email, password) VALUES ('edson', 'edson@gmail.com','12345');

insert into categories (user_id, title) values (1,'Casa'),(1,'Educação'),(1,'Entreterimento');

insert into wallets (user_id, title) values (1,'Padrão');
