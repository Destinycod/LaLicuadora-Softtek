package com.lalicuadora.app.domain.models.entities.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "base_prod_x_possible_customization")
@Getter
@Setter
public class BaseProdXPossibleCustom{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "base_product", referencedColumnName = "id")
    private BaseProduct baseProduct;

    @ManyToOne
    @JoinColumn(name = "possible_customization", referencedColumnName = "id")
    private PossibleCustomization possibleCustomization;

    @JsonIgnore
    @OneToMany(targetEntity = SpecificCustomization.class, mappedBy = "baseProdXPossibleCustom", cascade = CascadeType.ALL)
    private List<SpecificCustomization> specificCustomization;

    public BaseProdXPossibleCustom(){
        this.specificCustomization = new ArrayList<>();
    }

    public BaseProdXPossibleCustom(BaseProduct baseProduct, PossibleCustomization possibleCustomization){
        this();
        this.baseProduct = baseProduct;
        this.possibleCustomization = possibleCustomization;
    }

    @Override
    public String toString() {
        return "BaseProdXPossibleCustom{" +
                "id=" + id +
                ", baseProduct=" + baseProduct +
                ", possibleCustomization=" + possibleCustomization +
                ", specificCustomization=" + specificCustomization +
                '}';
    }
}