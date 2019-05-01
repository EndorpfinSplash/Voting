create table roles
(
  role_id int(20) auto_increment primary key,
  role_name varchar(50) not null,
  role_user_id int(20) not null,
  constraint ROL_USR_FK
    foreign key (role_user_id) references user (user_id)
      on delete cascade
)
  charset=utf8;

-- auto-generated definition
create table user
(
  user_id           int auto_increment
    primary key,
  user_name         varchar(100) not null,
  user_surname      varchar(50)  null,
  registration_date timestamp    null,
  login             varchar(100) not null,
  password          varchar(100) not null,
  constraint user_login_uindex
  unique (login)
);

-- auto-generated definition
create table users_roles
(
  user_id int null,
  role_id int null,
  constraint user_id_fk
  foreign key (user_id) references user (user_id),
  constraint role_name_fk
  foreign key (role_id) references roles (role_id)
);

create index role_name_fk
  on users_roles (role_id);

create index user_id_fk
  on users_roles (user_id);



