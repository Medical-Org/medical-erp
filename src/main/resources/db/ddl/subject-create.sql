create table if not exists subject(
    id uuid primary key ,
    name varchar(256) unique not null,
    created_date timestamp,
    updated_date timestamp
);