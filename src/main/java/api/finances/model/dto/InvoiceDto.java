package api.finances.model.dto;

import api.finances.model.Category;
import api.finances.model.Installment;
import api.finances.model.User;
import api.finances.model.Wallet;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class InvoiceDto {

    private Long id;

    private String invoice;

    private BigDecimal value;

    private String description;

    private String type;

    private LocalDate startDate;

    private Integer quantity;

    private CategoryDto category;

    private WalletDto wallet;

    private List<Installment> installments;
}
