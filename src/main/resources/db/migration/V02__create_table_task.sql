CREATE TABLE t_task (
    id bigserial NOT NULL,
    title varchar(150) NOT NULL,
    description varchar(255) NOT NULL,
    creation_date timestamp NOT NULL,
    conclusion_date timestamp NULL,
    status varchar(50) NOT NULL,
    CONSTRAINT t_task_id_pk PRIMARY KEY (id)
);