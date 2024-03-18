package api.finances.service;


import api.finances.exception.customExceptions.BadRequestException;
import api.finances.exception.customExceptions.NotFoundException;
import api.finances.model.Installment;
import api.finances.model.Invoice;
import api.finances.repository.InvoiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private InstallmentService installmentService;

    @Transactional
    public void create(Invoice invoice) {

        validations(invoice);

        switch (invoice.getType().toLowerCase()) {
            case "unica":
                typeUnique(invoice);
                break;
            case "parcelada":
                typeInstallment(invoice);
                break;
            case "fixa":
                typeFixed(invoice);
                break;
            default:
                throw new BadRequestException("O tipo de lançamento tem que ser unica, parcelada ou fixa!");
        }
    }

    public Invoice findByIdAndUser(Long id, Long user_id) {

        this.userService.findById(user_id);

        return this.invoiceRepository.findByIdAndUserId(id, user_id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public List<Invoice> getInvoices(Long user_id) {
        return this.invoiceRepository.findByUserId(user_id);
    }

    @Transactional
    public void update(Long id, Invoice invoice) {

        this.validations(invoice);

        Invoice currentInvoice = this.findByIdAndUser(id, invoice.getUser().getId());

        currentInvoice.setValue(invoice.getValue());
        currentInvoice.setDescription(invoice.getDescription());
        currentInvoice.setStartDate(invoice.getStartDate());
        currentInvoice.setCategory(invoice.getCategory());
        currentInvoice.setWallet(invoice.getWallet());

        List<Installment> installments = currentInvoice.getInstallments();

        installments.forEach(installment -> {

            if (currentInvoice.getType().equals("unica")) {
                installment.setValue(invoice.getValue());
                installment.setDate(invoice.getStartDate());
            }

            if (currentInvoice.getType().equals("parcelada")) {
                installment.setValue(invoice.getValue().divide(BigDecimal.valueOf(currentInvoice.getQuantity()), 2, RoundingMode.CEILING));
                installment.setDate(invoice.getStartDate().plusMonths(installment.getInstallment()));
            }

            if (currentInvoice.getType().equals("fixa")) {
                installment.setValue(invoice.getValue());
                installment.setDate(invoice.getStartDate().plusMonths(installment.getInstallment()));
            }

        });

        this.invoiceRepository.save(currentInvoice);

    }

    public void delete(Long id, Long user_id) {

        this.findByIdAndUser(id, user_id);

        this.invoiceRepository.deleteById(id);
    }

    private void validations(Invoice invoice) {
        this.userService.findById(invoice.getUser().getId());
        this.categoryService.findByUserId(invoice.getCategory().getId(), invoice.getUser().getId());
        this.walletService.findByUserId(invoice.getWallet().getId(), invoice.getUser().getId());
    }

    private void typeInstallment(Invoice invoice) {

        for (int i = 1; i < invoice.getQuantity() + 1; i++) {

            Installment installment = new Installment();

            installment.setValue(invoice.getValue().divide(BigDecimal.valueOf(invoice.getQuantity()), 2, RoundingMode.CEILING));
            installment.setInstallment(i);
            installment.setStatus(false);
            installment.setDate(invoice.getStartDate().plusMonths(i));
            installment.setInvoice(invoice);

            invoice.getInstallments().add(installment);

        }
        this.invoiceRepository.save(invoice);

    }

    private void typeUnique(Invoice invoice) {

        Installment installment = new Installment();

        installment.setValue(invoice.getValue());
        installment.setInstallment(1);
        installment.setStatus(false);
        installment.setDate(invoice.getStartDate());
        installment.setInvoice(invoice);

        invoice.getInstallments().add(installment);

        this.invoiceRepository.save(invoice);
    }


    private void typeFixed(Invoice invoice) {

        for (int i = 1; i < invoice.getQuantity() + 1; i++) {

            Installment installment = new Installment();

            installment.setValue(invoice.getValue());
            installment.setInstallment(i);
            installment.setStatus(false);
            installment.setDate(invoice.getStartDate().plusMonths(i));
            installment.setInvoice(invoice);

            invoice.getInstallments().add(installment);
        }

        this.invoiceRepository.save(invoice);
    }


}
