package br.com.simplified_picpay.simplified_picpay.repositories;

import br.com.simplified_picpay.simplified_picpay.domain.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
