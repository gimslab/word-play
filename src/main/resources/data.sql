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
	createdTime timestamp,
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
	primary key(id),
	key user_word_idx01(userId, wordBookId, proficiency)
);

create table if not exists word_book (
	id bigint not null AUTO_INCREMENT,
	title varchar(255),
	createdTime timestamp,
	primary key(id)
)

insert into word_book (title, filename, createdTime) values
 ('wordbook5000-all','wordbook5000-all.tsv',now())
,('wordbook5000-day01','wordbook5000-day01.tsv',now())
,('wordbook5000-day02','wordbook5000-day02.tsv',now())
,('wordbook5000-day03','wordbook5000-day03.tsv',now())
,('wordbook5000-day04','wordbook5000-day04.tsv',now())
,('wordbook5000-day05','wordbook5000-day05.tsv',now())
,('wordbook5000-day06','wordbook5000-day06.tsv',now())
,('wordbook5000-day07','wordbook5000-day07.tsv',now())
,('wordbook5000-day08','wordbook5000-day08.tsv',now())
,('wordbook5000-day09','wordbook5000-day09.tsv',now())
,('wordbook5000-day10','wordbook5000-day10.tsv',now())
,('wordbook5000-day11','wordbook5000-day11.tsv',now())
,('wordbook5000-day12','wordbook5000-day12.tsv',now())
,('wordbook5000-day13','wordbook5000-day13.tsv',now())
,('wordbook5000-day14','wordbook5000-day14.tsv',now())


