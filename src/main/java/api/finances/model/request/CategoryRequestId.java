package api.finances.model.request;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestId {

    @NotNull
    private Long id;

}
