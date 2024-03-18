package api.finances.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "installments")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private BigDecimal value;

    private Boolean status;

    private LocalDate date;

    private int installment;

    @JsonIgnore
    @ManyToOne
    private Invoice invoice;
}
