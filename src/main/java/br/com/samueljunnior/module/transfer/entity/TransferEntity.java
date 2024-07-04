package br.com.samueljunnior.module.transfer.entity;

import br.com.samueljunnior.module.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_transferencia")
public class TransferEntity {
    public static final String COD_TRANSFER_ID = "id_tranferencia";

    @Id
    @Column(name = COD_TRANSFER_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_valor_tranferencia")
    private BigDecimal value;

    @Column(name = "dt_tranferencia")
    private LocalDateTime transferDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario_pagador")
    private UserEntity sender;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario_recebedor")
    private UserEntity receiver;
}


