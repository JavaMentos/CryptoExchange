-- changeset :04_create_table_user_roles.sql
-- Name: user_roles; Type: TABLE; schema: public

CREATE TABLE IF NOT EXISTS public.user_roles
(
    user_id BIGINT,
    role_id BIGINT
);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id) REFERENCES public.users(id)
            ON DELETE CASCADE;

ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_roles_roles
        FOREIGN KEY (role_id) REFERENCES public.roles(id)
            ON DELETE CASCADE;