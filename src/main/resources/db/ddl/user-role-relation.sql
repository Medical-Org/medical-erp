create table if not exists user_role(
    user_id uuid references users(id),
    role_id uuid references role(id),
    primary key (user_id,role_id)
);