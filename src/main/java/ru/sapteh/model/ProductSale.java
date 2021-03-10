package ru.sapteh.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "productsale")
public class ProductSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private Date saleDate;
    @NonNull
    private int quantity;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @NonNull
    private Product product;
}
