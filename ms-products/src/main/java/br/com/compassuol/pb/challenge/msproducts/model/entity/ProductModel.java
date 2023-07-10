package br.com.compassuol.pb.challenge.msproducts.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="tb_product")
public class ProductModel implements Serializable {

    @Id
    @Column(name="id", length=36, nullable=false)
    private String id;

    @Column(name="name", length=100, nullable=false, unique=true)
    @Check(constraints = "LENGTH(name)>=3")
    private String name;

    @Column(name="description", length=300, nullable=false)
    @Check(constraints = "LENGTH(description)>=3")
    private String description;

    @Column(name="date", columnDefinition="DATETIME NOT NULL", nullable=false)
    private Date date;

    @Column(name="price", columnDefinition="NUMERIC(7,2) NOT NULL", nullable=false, precision=7, scale=2)
    private BigDecimal price;

    @Column(name="img_url", length=200, nullable=false)
    @Check(constraints = "LENGTH(img_url)>=12")
    private String imgUrl;

    @ManyToMany
    @JoinTable(name="tb_product_category", joinColumns=@JoinColumn(name="product_id", columnDefinition="VARCHAR(36)"), inverseJoinColumns=@JoinColumn(name="category_id", columnDefinition="VARCHAR(36)"))
    private Set<CategoryModel> categories;

    public ProductModel() {
        this.id = UUID.randomUUID().toString();
    }
}
