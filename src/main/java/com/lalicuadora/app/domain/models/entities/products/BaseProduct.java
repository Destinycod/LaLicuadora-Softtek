package com.lalicuadora.app.domain.models.entities.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "base_product")
@Getter
@Setter
public class BaseProduct{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Min(1)
    @Column(name = "price")
    private Double price;

    @NotNull
    @Column(name = "description")
    private String description;

    @Column(name = "manufacturingTime")
    private String manufacturingTime;

    @JsonIgnore
    @OneToMany(targetEntity = BaseProdXPossibleCustom.class, mappedBy = "baseProduct", cascade = CascadeType.ALL)
    private List<BaseProdXPossibleCustom> baseProdXPossibleCustoms;

    @Column(name = "is_active")
    private boolean active;

    public BaseProduct(){
        this.active = true;
        this.baseProdXPossibleCustoms = new ArrayList<>();
    }

    public BaseProduct(String name, Double price, String description, String manufacturingTime) {
        this();
        this.name = name;
        this.price = price;
        this.description = description;
        this.manufacturingTime = manufacturingTime;
    }

    @Override
    public String toString() {
        return "BaseProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", manufacturingTime='" + manufacturingTime + '\'' +
                ", baseProdXPossibleCustoms=" + baseProdXPossibleCustoms +
                ", active=" + active +
                '}';
    }
}