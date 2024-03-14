package api.finances.model.request;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletRequest {

    @NotBlank
    private String title;
    private String description;

    @NotNull
    @Valid
    private UserRequestId user;
}
