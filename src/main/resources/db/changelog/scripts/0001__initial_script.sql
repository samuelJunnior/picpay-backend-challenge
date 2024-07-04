--liquibase formatted sql
--changeset samueljuniior:1.1
CREATE TABLE tb_tipo_usuario (
    id_tipo_usuario INTEGER PRIMARY KEY,
    des_descricao VARCHAR(20) NOT NULL
);

--changeset samueljuniior:1.2
CREATE TABLE tb_usuario (
    id_usuario SERIAL PRIMARY KEY,
    id_tipo_usuario INTEGER NOT NULL ,
    des_nome_completo VARCHAR(100) NOT NULL,
    des_documento VARCHAR(14) UNIQUE NOT NULL,
    des_email varchar(254) UNIQUE NOT NULL,
    des_senha VARCHAR(100) NOT NULL,
    num_saldo DECIMAL(17,2) NOT NULL DEFAULT 0,
    CONSTRAINT usuario_tipo_fk FOREIGN KEY(id_tipo_usuario) REFERENCES tb_tipo_usuario(id_tipo_usuario)
);

--changeset samueljuniior:1.3
INSERT INTO tb_tipo_usuario VALUES (1, 'COMUNS');
INSERT INTO tb_tipo_usuario VALUES (2, 'LOJISTAS');