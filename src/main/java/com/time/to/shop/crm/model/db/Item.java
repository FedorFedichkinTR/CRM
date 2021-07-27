package com.time.to.shop.crm.model.db;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "items_seq")
    @SequenceGenerator(name = "items_seq", sequenceName = "items_seq", allocationSize = 1)
    private Long id;
    private String name;
    private Integer initialPrice;
    private Integer quantity;
    private LocalDateTime dateOfPurchase;
    private String notes;

}
