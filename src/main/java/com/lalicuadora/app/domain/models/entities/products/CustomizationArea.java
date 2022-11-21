package com.lalicuadora.app.domain.models.entities.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customization_area")
@Setter
@Getter
public class CustomizationArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @JsonIgnore
    @OneToMany(targetEntity = PossibleCustomization.class, mappedBy = "customizationArea", cascade = CascadeType.ALL)
    private List<PossibleCustomization> possibleCustomizations;

    @NotNull
    @Column(name = "place")
    private String place;

    public CustomizationArea(){
        this.possibleCustomizations = new ArrayList<>();
    }

    public CustomizationArea(String place) {
        this();
        this.place = place;
    }

    @Override
    public String toString() {
        return "CustomizationArea{" +
                "id=" + id +
                ", possibleCustomizations=" + possibleCustomizations +
                ", place='" + place + '\'' +
                '}';
    }
}