package com.lalicuadora.app.domain.models.entities.shops;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lalicuadora.app.domain.models.entities.products.CustomProduct;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publication")
@Setter
@Getter
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @ManyToOne
    @JoinColumn(name = "custom_product", referencedColumnName = "id")
    private CustomProduct customProduct;

    @ManyToOne()
    @JoinColumn(name = "shop", referencedColumnName = "id")
    private Shop shop;

    @JsonIgnore
    @OneToMany(targetEntity = PublicationXPublicationStatus.class, mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PublicationXPublicationStatus> publicationXPublicationStatus;

    public Publication(){
        this.publicationXPublicationStatus = new ArrayList<>();
    }

    public Publication(String name, LocalDate publicationDate, CustomProduct customProduct, Shop shop) {
        this();
        this.name = name;
        this.publicationDate = publicationDate;
        this.customProduct = customProduct;
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publicationDate=" + publicationDate +
                ", customProduct=" + customProduct +
                ", shop=" + shop +
                ", publicationXPublicationStatus=" + publicationXPublicationStatus +
                '}';
    }
}
