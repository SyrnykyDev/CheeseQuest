create table users(
    id bigserial primary key,
    username text not null,
    password text not null,
    role text not null,
    avatar text not null default 'none.png'
)