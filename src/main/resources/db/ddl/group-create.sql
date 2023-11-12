create table if not exists groups(
    id uuid primary key ,
    name varchar(256) unique not null ,
    curator_id uuid references users(id) unique,
    created_date timestamp default current_timestamp,
    updated_date timestamp default current_timestamp
);