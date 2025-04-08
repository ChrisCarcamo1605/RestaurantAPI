alter table users add user_type bigint not null;
alter table users add constraint  foreign key (user_type) references user_type(type_id) ON DELETE CASCADE ;