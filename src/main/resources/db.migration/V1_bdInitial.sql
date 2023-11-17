-- create user table
create table user (
                         id integer not null,
                         username varchar(150) not null,
                         password varchar(150) not null,
                         email varchar(150),
                         created_at timestamp not null,
                         primary key (id)
);

-- create a sequence for user ide
create sequence user_sequence as integer increment 1;


-- create rol table
create table rol (
                        id integer not null,
                        name varchar(100) not null,
                        primary key (id)
);
create sequence rol_sequence as integer increment 1;

-- create user_detailed table
create table user_detail (
                                 id integer not null,
                                 first_name varchar(100) not null,
                                 last_name varchar(100) not null,
                                 age int,
                                 birth_day date,
                                 user_id int,
                                 primary key (id)
);
alter table user_detail add constraint fk_user_id_rol_Ref_Rol foreign key (user_id)
    references rol (id) on delete restrict on update restrict;

create sequence userDetail_sequence as integer increment 1;


-- create user_rol table
create table user_rol (
                            id bigint not null,
                            active boolean not null,
                            created_at timestamp not null,
                            user_id integer,
                            rol_id integer,
                            primary key (id)
);
create sequence userRol_sequence as integer increment 1;

alter table user_rol add constraint FK_User_rol_Ref_User foreign key (user_id)
    references user (id) on delete restrict on update restrict;

alter table user_rol add constraint FK_User_rol_Ref_Rol foreign key (rol_id)
    references user (id) on delete restrict on update restrict;