package api.finances.model.request;


import api.finances.model.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {

    private Long id;
    @NotBlank
    private String title;
    private String description;
    @NotNull
    @Valid
    private UserRequestId user;
}
