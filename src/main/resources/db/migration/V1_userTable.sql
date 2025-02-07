create table if not exists users(
                      id bigserial primary key,
                      username text not null default ('user' + id*localtime),
                      email text not null unique,
                      password text not null,
                      role text not null default 'USER',
                      avatar text not null default 'none.png'
)