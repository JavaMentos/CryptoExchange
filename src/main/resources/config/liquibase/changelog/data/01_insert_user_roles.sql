-- changeset :01_insert_user_roles.sql
-- Name: user_roles; Type: TABLE; schema: public

INSERT INTO public.roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN')
ON CONFLICT DO NOTHING;