package br.com.compassuol.pb.challenge.msproducts.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="tb_category")
public class CategoryModel implements Serializable {

    @Id
    @Column(name="id", length=36, nullable=false)
    private String id;

    @Column(name="name", length=50, nullable=false, unique=true)
    @Check(constraints = "LENGTH(name)>=3")
    private String name;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<ProductModel> products;

    public CategoryModel() {
        this.id = UUID.randomUUID().toString();
    }
}
