alter table users add column email text unique ;

DROP TABLE users CASCADE;
