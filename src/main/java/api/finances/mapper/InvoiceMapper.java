package api.finances.mapper;

import api.finances.model.Invoice;
import api.finances.model.dto.InvoiceDto;
import api.finances.model.request.InvoiceRequest;
import api.finances.model.request.InvoiceRequestUpdate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceMapper {

    
    @Autowired
    private ModelMapper modelMapper;

    public InvoiceDto toDto(Invoice invoice) {
        return this.modelMapper.map(invoice, InvoiceDto.class);
    }

    public Invoice toModel(InvoiceDto invoiceDto) {
        return this.modelMapper.map(invoiceDto, Invoice.class);
    }

    public Invoice toDomainModel(InvoiceRequest invoiceRequest) {
        return this.modelMapper.map(invoiceRequest, Invoice.class);
    }

    public Invoice toDomainModelUpdate(InvoiceRequestUpdate invoiceRequestUpdate) {
        return this.modelMapper.map(invoiceRequestUpdate, Invoice.class);
    }

    public List<InvoiceDto> toListDto(List<Invoice> invoiceList) {
        return invoiceList.stream().map(this::toDto).collect(Collectors.toList());
    }

}

