
drop table if exists skill_value;
drop table if exists skill;
drop table if exists category;
drop table if exists user_role;
drop table if exists user;
drop table if exists role;

create table user (
	username varchar(255) not null,
	password varchar(255) not null,
	primary key (username)
);

create table role (
	name varchar(255) not null,
	primary key (name)
);

create table user_role (
	user varchar(255) not null,
	role varchar(255) not null,
	primary key (user, role),
	foreign key (user) references user(username),
	foreign key (role) references role(name)
);

create table category (
	name varchar(255) not null,
	primary key (name)
);

create table skill (
	name varchar(255) not null,
	category varchar(255) not null,
	primary key (name),
	foreign key (category) references category(name)
);

create table skill_value (
	user varchar(255) not null,
	skill varchar(255) not null,
	value int not null,
	primary key (user, skill),
	foreign key (user) references user(username),
	foreign key (skill) references skill(name)
);