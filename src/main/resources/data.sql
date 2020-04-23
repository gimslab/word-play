--drop table IF EXISTS user_word;

create table if not exists user_word (
	id bigint not null AUTO_INCREMENT,
	userId varchar(255),
	word varchar(255),
	proficiency int,
	primary key(id),
	key user_word_idx01(userId, word)
);

--insert into member (name, age) values
--  ('Aliko', 11),
--  ('Bill', 22),
--  ('Folrunsho', 33);