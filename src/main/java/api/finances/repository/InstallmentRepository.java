package api.finances.repository;

import api.finances.model.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {

    void deleteByInvoiceId(Long invoice_id);

}
