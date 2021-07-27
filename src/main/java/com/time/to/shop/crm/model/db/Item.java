package com.time.to.shop.crm.model.db;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfPurchase;
    private String notes;

}
