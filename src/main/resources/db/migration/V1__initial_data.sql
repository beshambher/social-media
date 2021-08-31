create table role (id varchar(36) not null, created_at datetime, is_editable boolean default true, name varchar(50) not null, updated_at datetime, primary key (id));
create table user (avatar varchar(255), created_at datetime, email varchar(100) not null, first_name varchar(20) not null, last_name varchar(30), location varchar(255), updated_at datetime, username varchar(100) not null, role_id varchar(36), primary key (username));

alter table user add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);
alter table user add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);
alter table user add constraint FKn82ha3ccdebhokx3a8fgdqeyy foreign key (role_id) references role (id);

insert into role (id, created_at, is_editable, name, updated_at) VALUES ('123e4567-e89b-42d3-a456-556642440000', now(), false, 'ROLE_ADMIN', now());	
insert into role (id, created_at, is_editable, name, updated_at) VALUES ('123e4567-e89b-42d3-a456-556642440001', now(), false, 'ROLE_USER', now());	

insert into user (avatar, created_at, email, first_name, last_name, location, updated_at, username, role_id) values ('https://avatars.githubusercontent.com/u/18172011?v=4',now(),'bishamberc@gmail.com','Beshambher','Chaukhwan','India',now(),'beshamberc','123e4567-e89b-42d3-a456-556642440000');
