--liquibase formatted sql
--changeset samueljuniior:3.1
CREATE TABLE tb_carteira (
    id_carteira SERIAL PRIMARY KEY,
    id_usuario INTEGER NOT NULL,
    dt_criacao TIMESTAMP NOT NULL,
    num_saldo DECIMAL(17,2) NOT NULL DEFAULT 0,
    CONSTRAINT usuario_carteira_fk FOREIGN KEY(id_usuario) REFERENCES tb_usuario(id_usuario)
);

INSERT INTO tb_carteira(id_usuario, dt_criacao, num_saldo)
    (SELECT u.id_usuario, current_timestamp, u.num_saldo FROM tb_usuario u);

ALTER TABLE tb_usuario
    DROP COLUMN num_saldo;