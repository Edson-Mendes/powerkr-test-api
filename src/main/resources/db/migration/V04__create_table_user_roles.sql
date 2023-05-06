CREATE TABLE t_user_roles(
    user_id bigint NOT NULL,
    role_id int NOT NULL,
    CONSTRAINT t_user_roles_user_id_fk FOREIGN KEY (user_id) REFERENCES t_user(id),
    CONSTRAINT t_user_roles_role_id_fk FOREIGN KEY (role_id) REFERENCES t_role(id)
);