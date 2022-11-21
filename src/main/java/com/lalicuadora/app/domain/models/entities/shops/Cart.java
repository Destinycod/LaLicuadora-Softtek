package com.lalicuadora.app.domain.models.entities.shops;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lalicuadora.app.domain.models.entities.users.Client;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    private Client client;

    @JsonIgnore
    @OneToMany(targetEntity = Item.class, mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> item;

    @ManyToOne
    @JoinColumn(name = "shop", referencedColumnName = "id")
    private Shop shop;

    @NotNull
    @Column(name = "total")
    private Double totalPrice;

    public Cart(){
        this.item = new ArrayList<>();
    }

    public Cart(Client client, Shop shop, Double totalPrice) {
        this();
        this.client = client;
        this.shop = shop;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", client=" + client +
                ", item=" + item +
                ", shop=" + shop +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
