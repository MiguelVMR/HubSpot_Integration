INSERT INTO tb_roles (name) VALUES ('admin')
    ON CONFLICT (name) DO NOTHING;

INSERT INTO tb_roles (name) VALUES ('basic')
    ON CONFLICT (name) DO NOTHING;
