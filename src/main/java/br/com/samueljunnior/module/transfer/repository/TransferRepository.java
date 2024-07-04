package br.com.samueljunnior.module.transfer.repository;

import br.com.samueljunnior.module.transfer.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<TransferEntity, Long> {
}
