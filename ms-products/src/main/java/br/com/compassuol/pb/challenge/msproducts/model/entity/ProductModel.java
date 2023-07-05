package br.com.compassuol.pb.challenge.msproducts.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "tb_product")
public class ProductModel implements Serializable {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false, precision = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private String imgUrl;

    @ManyToMany
    @JoinTable(name = "tb_product_category")
    private Set<CategoryModel> categories;

    public ProductModel() {
        this.id = UUID.randomUUID().toString();
    }
}
