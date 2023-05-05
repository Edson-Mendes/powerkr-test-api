CREATE TABLE t_user (
    id bigserial NOT NULL,
    name varchar(100) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    CONSTRAINT t_user_id_pk PRIMARY KEY (id)
);