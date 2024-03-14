package api.finances.repository;

import api.finances.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query("from Wallet w where w.id = :id and w.user.id = :user_id")
    Optional<Wallet> findByUserId(Long id, Long user_id);

}
