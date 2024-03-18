package api.finances.model.request;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletRequestId {

    @NotNull
    private Long id;

}
