package com.lalicuadora.app.domain.models.entities.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lalicuadora.app.domain.models.entities.products.Customization;
import com.lalicuadora.app.domain.models.entities.shops.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "seller")
@Getter
@Setter
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @JsonIgnore
    @OneToMany(targetEntity = Customization.class, mappedBy = "seller", cascade = CascadeType.ALL)
    private Set<Customization> customization;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PaymentMethod.class)
    private Set<PaymentMethod> paymentMethod;

    public Seller() {
        this.paymentMethod = new HashSet<>();
        this.customization = new HashSet<>();
    }

    public Seller(String name, String lastName) {
        this();
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", customization=" + customization +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
