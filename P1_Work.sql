
-- working on P1 tables 
DROP TABLE IF EXISTS expense_reimbursements;
DROP TABLE IF EXISTS users;


-- NEED TO ADD STILL 
create table users(
user_id SERIAL,
user_first_name VARCHAR(50) not null, 
user_last_name VARCHAR(50) not null,
user_name VARCHAR(200) not null unique  ,
"password" VARCHAR(200) not null ,
status VARCHAR(20) not null, 
constraint users_pk primary key (user_id)
);

create table expense_reimbursements(
ex_id SERIAL,
ex_user_name VARCHAR(200) not null,
ex_date VARCHAR(10) not null,
details VARCHAR(2000) not null,
amount DECIMAL(10,2) not null,
ex_status VARCHAR(20) not null,
constraint ex_user_name foreign key (ex_user_name) references users(user_name)
);


select * from users;

select * from expense_reimbursements;

