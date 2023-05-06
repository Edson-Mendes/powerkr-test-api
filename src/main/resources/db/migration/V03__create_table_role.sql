CREATE TABLE t_role(
    id serial NOT NULL,
    role_name varchar(50) NOT NULL,
    CONSTRAINT t_role_id_pk PRIMARY KEY (id),
    CONSTRAINT t_role_role_name_unique UNIQUE (role_name)
);