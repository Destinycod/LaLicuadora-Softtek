package com.lalicuadora.app.domain.models.entities.shops;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lalicuadora.app.domain.models.entities.users.Client;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "purchase")
@Setter
@Getter
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @JsonIgnore
    @OneToMany(targetEntity = Item.class, mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    private Client client;

    @Column(name = "purchase_status")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PurchaseStatus.class)
    private Set<PurchaseStatus> purchaseStatus;

    //@Transient
    //private PaymentMethod paymentMethod;

    public Purchase() {
        this.items = new ArrayList<>();
        this.purchaseStatus = new HashSet<>();
    }

    public Purchase(LocalDateTime dateTime, Client client) {
        this();
        this.dateTime = dateTime;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", items=" + items +
                ", client=" + client +
                ", purchaseStatus=" + purchaseStatus +
                '}';
    }

    /*public void setPaymentMethod(PaymentMethod paymentMethod) {
        Seller seller = new Seller();
        paymentMethod = seller.getPaymentMethod();
        this.paymentMethod = paymentMethod;
    }*/
}
