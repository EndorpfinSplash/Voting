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