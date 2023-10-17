-- changeset :03_create_table_roles.sql
-- Name: roles; Type: TABLE; schema: public

CREATE TABLE IF NOT EXISTS public.roles (
                       id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
                       name VARCHAR(100) NOT NULL UNIQUE,
                       status VARCHAR(25) NOT NULL DEFAULT 'ACTIVE'
);