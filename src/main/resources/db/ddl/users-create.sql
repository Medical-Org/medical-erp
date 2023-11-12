create table if not exists users(
    id uuid primary key ,
    first_name varchar(256) not null,
    last_name varchar(256) not null ,
    middle_name varchar(256),
    email varchar(100) unique ,
    phone_number varchar(25) unique ,
    password text not null ,
    subject_id uuid references subject(id),
    is_active boolean default true,
    is_verify boolean default false,
    created_date timestamp,
    updated_date timestamp
);