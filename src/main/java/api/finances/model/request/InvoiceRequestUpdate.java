package api.finances.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
public class InvoiceRequestUpdate {

    @NotNull
    private BigDecimal value;

    private String description;

    private LocalDate startDate;

    @NotNull
    @Valid
    private UserRequestId user;

    @NotNull
    @Valid
    private CategoryRequestId category;

    @NotNull
    @Valid
    private WalletRequestId wallet;
}
