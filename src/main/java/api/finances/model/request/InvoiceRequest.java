package api.finances.model.request;

import api.finances.model.dto.CategoryDto;
import api.finances.model.dto.UserDto;
import api.finances.model.dto.WalletDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
public class InvoiceRequest {

    private Long id;

    @NotBlank
    private String invoice;

    @NotNull
    private BigDecimal value;

    private String description;

    @NotBlank
    private String type;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private Integer quantity;

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
