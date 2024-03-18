package api.finances.controller;


import api.finances.mapper.InvoiceMapper;
import api.finances.model.Invoice;
import api.finances.model.dto.InvoiceDto;
import api.finances.model.request.InvoiceRequest;
import api.finances.model.request.InvoiceRequestUpdate;
import api.finances.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @PostMapping
    public void launch(@Valid @RequestBody InvoiceRequest invoiceRequest){

        Invoice invoice = this.invoiceMapper.toDomainModel(invoiceRequest);

        this.invoiceService.create(invoice);
    }

    @GetMapping("/user/{id}")
    public List<InvoiceDto> getInvoices(@PathVariable Long id){
        List<Invoice> invoices = this.invoiceService.getInvoices(id);

        return this.invoiceMapper.toListDto(invoices);
    }

    @GetMapping("/{id}/user/{user_id}")
    public InvoiceDto findByIdAndUserId(@PathVariable Long id, @PathVariable Long user_id){

        Invoice invoice = this.invoiceService.findByIdAndUser(id, user_id);

        return this.invoiceMapper.toDto(invoice);

    }

    @PutMapping("/{id}/user/{user_id}")
    public void update(@PathVariable Long id, @PathVariable Long user_id, @Valid @RequestBody InvoiceRequestUpdate invoiceRequestUpdate){

        Invoice domainModelUpdate = this.invoiceMapper.toDomainModelUpdate(invoiceRequestUpdate);

        this.invoiceService.update(id, domainModelUpdate);
    }

    @DeleteMapping("/{id}/user/{user_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id,@PathVariable Long user_id){
        this.invoiceService.delete(id, user_id);
    }


}
