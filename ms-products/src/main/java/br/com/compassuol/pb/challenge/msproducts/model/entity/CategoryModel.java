package br.com.compassuol.pb.challenge.msproducts.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "tb_category")
public class CategoryModel implements Serializable {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<ProductModel> products;

    public CategoryModel() {
        this.id = UUID.randomUUID().toString();
    }
}
