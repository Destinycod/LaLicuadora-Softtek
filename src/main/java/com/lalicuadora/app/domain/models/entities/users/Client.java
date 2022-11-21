package com.lalicuadora.app.domain.models.entities.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lalicuadora.app.domain.models.entities.shops.Cart;
import com.lalicuadora.app.domain.models.entities.shops.Purchase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Setter
@Getter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @JsonIgnore
    @OneToMany(targetEntity = Cart.class, mappedBy = "client", cascade = CascadeType.ALL)
    private List<Cart> carts;

    @JsonIgnore
    @OneToMany(targetEntity = Purchase.class, mappedBy = "client", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    public Client(){
        this.carts = new ArrayList<>();
        this.purchases = new ArrayList<>();
    }

    public Client(String name, String lastName){
        this();
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", carts=" + carts +
                ", purchases=" + purchases +
                '}';
    }
}
