package com.lalicuadora.app.domain.models.entities.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "possible_customization")
@Getter
@Setter
public class PossibleCustomization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customization_area", referencedColumnName = "id")
    private CustomizationArea customizationArea;

    @Column(name = "customization_type")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = CustomizationType.class)
    private List<CustomizationType> customizationTypes;

    @JsonIgnore
    @OneToMany(targetEntity = BaseProdXPossibleCustom.class, mappedBy = "possibleCustomization", cascade = CascadeType.ALL)
    private List<BaseProdXPossibleCustom> baseProdXPossibleCustoms;

    public PossibleCustomization() {
        this.customizationTypes = new ArrayList<>();
        this.baseProdXPossibleCustoms = new ArrayList<>();
    }

    public PossibleCustomization(CustomizationArea customizationArea){
        this();
        this.customizationArea = customizationArea;
    }

    @Override
    public String toString() {
        return "PossibleCustomization{" +
                "id=" + id +
                ", customizationArea=" + customizationArea +
                ", customizationTypes=" + customizationTypes +
                ", baseProdXPossibleCustoms=" + baseProdXPossibleCustoms +
                '}';
    }
}
