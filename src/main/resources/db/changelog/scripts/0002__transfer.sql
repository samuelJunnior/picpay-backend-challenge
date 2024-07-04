--liquibase formatted sql
--changeset samueljuniior:2.1
CREATE TABLE tb_transferencia (
    id_tranferencia SERIAL PRIMARY KEY,
    id_usuario_pagador INTEGER NOT NULL,
    id_usuario_recebedor INTEGER NOT NULL,
    num_valor_tranferencia DECIMAL(17,2),
    dt_tranferencia TIMESTAMP,
    CONSTRAINT pagador_trasf_fk FOREIGN KEY(id_usuario_pagador) REFERENCES tb_usuario(id_usuario),
    CONSTRAINT recebedor_tranf_fk FOREIGN KEY(id_usuario_recebedor) REFERENCES tb_usuario(id_usuario)
);