package api.finances.repository;

import api.finances.model.Invoice;
import api.finances.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("from Invoice i where i.id = :id and i.user.id = :user_id")
    Optional<Invoice> findByIdAndUserId(Long id, Long user_id);

    List<Invoice> findByUserId(Long user);

}
