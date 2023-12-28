package com.ecomerce.dograshopping.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseModel{
    private String title;
    private String description;
    private int price;
    private String image;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "Category Details")
//    @JsonManagedReference
    private Category category;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name                  = "myProduct_orders",
//               joinColumns           = @JoinColumn(name = "myProduct_id"),
//               inverseJoinColumns    = @JoinColumn(name = "myOrder_id"))
//    private List<Orders> orders = new ArrayList<>();
}
