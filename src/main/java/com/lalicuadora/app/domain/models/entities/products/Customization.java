package com.lalicuadora.app.domain.models.entities.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lalicuadora.app.domain.models.entities.users.Seller;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customization")
@Getter
@Setter
public class Customization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Min(1)
    @Column(name = "price")
    private Double price;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "seller", referencedColumnName = "id")
    private Seller seller;

    @JsonIgnore
    @OneToMany(targetEntity = SpecificCustomization.class, mappedBy = "customizations", cascade = CascadeType.ALL)
    private List<SpecificCustomization> specificCustomization;

    public Customization(){
        this.specificCustomization = new ArrayList<>();
    }

    public Customization(String name, Double price, String content, Seller seller) {
        this();
        this.name = name;
        this.price = price;
        this.content = content;
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Customization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", content='" + content + '\'' +
                ", seller=" + seller +
                ", specificCustomization=" + specificCustomization +
                '}';
    }
}