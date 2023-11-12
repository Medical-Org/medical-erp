alter table users
add column group_id uuid references groups(id);