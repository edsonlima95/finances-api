package api.finances.model.dto;


import api.finances.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletDto {

    private Long id;
    private String title;
    private String description;
    private UserDto user;
}
