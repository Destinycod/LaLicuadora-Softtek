package com.lalicuadora.app.domain.models.entities.shops;

import com.lalicuadora.app.domain.models.entities.products.CustomProduct;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item")
@Setter
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "custom_product", referencedColumnName = "id")
    private CustomProduct customProduct;

    @ManyToOne
    @JoinColumn(name = "cart", referencedColumnName = "id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "purchase", referencedColumnName = "id")
    private Purchase purchase;

    @NotNull
    @Column(name = "amount")
    private int amount;

    @NotNull
    @Column(name = "price")
    private Double price;

    public Item(){
    }

    public Item(CustomProduct customProduct, Cart cart, Purchase purchase, int amount, Double price){
        this();
        this.customProduct = customProduct;
        this.cart = cart;
        this.purchase = purchase;
        this.amount = amount;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", customProduct=" + customProduct +
                ", cart=" + cart +
                ", purchase=" + purchase +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
