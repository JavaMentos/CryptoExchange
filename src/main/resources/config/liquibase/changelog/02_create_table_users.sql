-- changeset :02_create_table_users.sql
-- Name: users; Type: TABLE; schema: public

CREATE TABLE IF NOT EXISTS public.users
(
    id       BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    status   VARCHAR(25)  NOT NULL DEFAULT 'ACTIVE'
);