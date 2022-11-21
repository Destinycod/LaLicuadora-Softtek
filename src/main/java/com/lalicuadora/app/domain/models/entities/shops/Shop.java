package com.lalicuadora.app.domain.models.entities.shops;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shop")
@Getter
@Setter
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(targetEntity = Publication.class, mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publication> publications;

    @JsonIgnore
    @OneToMany(targetEntity = Publication.class, mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cart> cart;

    public Shop() {
        this.publications = new ArrayList<>();
        this.cart = new HashSet<>();
    }

    public Shop(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", publications=" + publications +
                ", cart=" + cart +
                '}';
    }
}
