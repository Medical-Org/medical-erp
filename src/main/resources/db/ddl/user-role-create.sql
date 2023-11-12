create table if not exists role(
    id uuid primary key ,
    name varchar(50) unique not null ,
    permissions varchar[],
    created_date timestamp,
    updated_date timestamp
);