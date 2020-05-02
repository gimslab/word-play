drop table if exists word;
create table if not exists word (
	id bigint not null AUTO_INCREMENT,
	wordBookId bigint,
	eng varchar(255),
	kor varchar(255),
	primary key(id),
	key word_idx01(wordBookId)
);

drop table if exists user;
create table if not exists user (
	id bigint not null AUTO_INCREMENT,
	signId varchar(255),
	createdAt timestamp,
	modifiedAt timestamp,
	deleted boolean,
	primary key(id),
	key user_idx01(signId)
);

drop table if exists user_word;
create table if not exists user_word (
	id bigint not null AUTO_INCREMENT,
	userId bigint,
	wordBookId bigint,
	wordId bigint,
	proficiency int,
	createdAt timestamp,
	modifiedAt timestamp,
	primary key(id),
	key user_word_idx01(userId, wordBookId, proficiency)
);

drop table if exists word_book;
create table if not exists word_book (
	id bigint not null AUTO_INCREMENT,
	title varchar(255),
	createdAt timestamp,
	modifiedAt timestamp,
	primary key(id)
)


