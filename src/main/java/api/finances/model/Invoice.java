package api.finances.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "invoices")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String invoice;

    private BigDecimal value;

    private String description;

    private String type;

    private LocalDate startDate;

    private int quantity;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Wallet wallet;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<Installment> installments = new ArrayList<>();

}
