package api.finances.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String name;
    private String email;

}