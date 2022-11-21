package com.lalicuadora.app.domain.models.entities.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "specific_customization")
@Getter
@Setter
public class SpecificCustomization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customization", referencedColumnName = "id")
    private Customization customizations;

    @ManyToOne
    @JoinColumn(name = "base_prod_x_possible_customization", referencedColumnName = "id")
    private BaseProdXPossibleCustom baseProdXPossibleCustom;

    @JsonIgnore
    @OneToMany(targetEntity = CustomProduct.class, mappedBy = "specificCustomization", cascade = CascadeType.ALL)
    private List<CustomProduct> customProducts;

    @NotNull
    @Column(name = "price")
    private Double price;

    public SpecificCustomization() {
        this.customProducts = new ArrayList<>();
    }

    public SpecificCustomization(Customization customizations, BaseProdXPossibleCustom baseProdXPossibleCustom, Double price) {
        this();
        this.customizations = customizations;
        this.baseProdXPossibleCustom = baseProdXPossibleCustom;
        this.price = price;
    }

    @Override
    public String toString() {
        return "SpecificCustomization{" +
                "customizations=" + customizations +
                ", baseProdXPossibleCustom=" + baseProdXPossibleCustom +
                ", customProducts=" + customProducts +
                ", price=" + price +
                '}';
    }
}
