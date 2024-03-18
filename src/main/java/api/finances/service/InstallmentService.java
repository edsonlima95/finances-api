package api.finances.service;


import api.finances.model.Installment;
import api.finances.repository.InstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstallmentService {

    @Autowired
    private InstallmentRepository installmentRepository;


    public void deleteByInvoiceId(Long invoice_id){
        this.installmentRepository.deleteByInvoiceId(invoice_id);
    }

}
