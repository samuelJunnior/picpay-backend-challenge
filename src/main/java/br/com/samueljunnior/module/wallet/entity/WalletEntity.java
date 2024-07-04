package br.com.samueljunnior.module.wallet.entity;

import br.com.samueljunnior.module.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_carteira")
public class WalletEntity {

    private static final String COD_ID_WALLET = "id_carteira";

    @Id
    @Column(name = COD_ID_WALLET)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dt_criacao")
    private LocalDateTime criationDate;

    @Column(name = "num_saldo")
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = UserEntity.ID_USER)
    private UserEntity user;

    public boolean hasBalance(BigDecimal value) {
        return  this.balance.compareTo(value) >= 0;
    }
}
