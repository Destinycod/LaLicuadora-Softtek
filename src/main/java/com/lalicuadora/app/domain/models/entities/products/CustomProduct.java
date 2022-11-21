package com.lalicuadora.app.domain.models.entities.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lalicuadora.app.domain.models.entities.shops.Item;
import com.lalicuadora.app.domain.models.entities.shops.Publication;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "custom_product")
@Setter
@Getter
public class CustomProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @JsonIgnore
    @OneToMany(targetEntity = Item.class, mappedBy = "customProduct", cascade = CascadeType.ALL)
    private List<Item> items;

    @JsonIgnore
    @OneToMany(targetEntity = Publication.class, mappedBy = "customProduct", cascade = CascadeType.ALL)
    private List<Publication> publications;

    @ManyToOne
    @JoinColumn(name = "specific_customization", referencedColumnName = "id")
    private SpecificCustomization specificCustomization;

    public CustomProduct(){
        this.items = new ArrayList<>();
        this.publications = new ArrayList<>();
    }

    public CustomProduct(SpecificCustomization specificCustomization) {
        this();
        this.specificCustomization = specificCustomization;
    }

    @Override
    public String toString() {
        return "CustomProduct{" +
                "id=" + id +
                ", items=" + items +
                ", publications=" + publications +
                ", specificCustomization=" + specificCustomization +
                '}';
    }
}
