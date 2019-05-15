create table poll
(
	poll_id int auto_increment	primary key,
	poll_question varchar(1000) not null
)
;

create table roles
(
	role_id int auto_increment primary key,
	role_name varchar(100) not null
)
;

create table users
(
	user_id int auto_increment primary key,
	user_name varchar(100) not null,
	user_surname varchar(100) null,
	registration_date timestamp null,
	login varchar(100) not null,
	password varchar(100) not null,
	constraint user_login_index
	unique (login)
)
;

create table users_roles
(
	user_id int null,
	role_id int null,
	constraint user_id_fk	foreign key (user_id) references users (user_id),
	constraint role_name_fk	foreign key (role_id) references roles (role_id)
)
;

create index role_name_fk
	on users_roles (role_id)
;

create index user_id_fk
	on users_roles (user_id)
;

create table variant_answers
(
  answer_id      int  auto_increment  not null primary key,
  variant_answer varchar(900)             null,
  answer_order   int                      null,
  сorrectness    binary(1)  null,
  poll_id        int                      null,
  constraint poll__fk foreign key (poll_id) references poll (poll_id)
);

create index poll__fk
  on variant_answers (poll_id);


create table users_answers
(
	answer_date timestamp null,
	user_id int null,
	answer_id int null,
	constraint answer_user_id_fk foreign key (user_id) references users (user_id),
	constraint answer_id_fk
	foreign key (answer_id) references variant_answers (answer_id)
)
;

create index answer_id_fk
	on users_answers (answer_id)
;

create index answer_user_id_fk
	on users_answers (user_id)
;



