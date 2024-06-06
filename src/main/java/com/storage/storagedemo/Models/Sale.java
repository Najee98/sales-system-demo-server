package com.storage.storagedemo.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.storage.storagedemo.Models.JoinTableEntities.SaleProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Date creationDate;
    Date lastModified;
    String seller;
    double total;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "sale_product",
//            joinColumns = @JoinColumn(name = "sale_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
//    List<Product> products = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleProduct> saleProducts = new ArrayList<>();
}
