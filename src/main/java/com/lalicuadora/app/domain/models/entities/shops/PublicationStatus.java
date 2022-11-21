package com.lalicuadora.app.domain.models.entities.shops;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publication_status")
@Setter
@Getter
public class PublicationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @JsonIgnore
    @OneToMany(targetEntity = PublicationXPublicationStatus.class, mappedBy = "publicationStatus", cascade = CascadeType.ALL)
    private List<PublicationXPublicationStatus> publicationXPublicationStatus;

    @Column(name = "real_status")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = RealStatus.class, fetch = FetchType.EAGER)
    private List<RealStatus> realStatus;

    public PublicationStatus(){
        this.realStatus = new ArrayList<>();
        this.publicationXPublicationStatus = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "PublicationStatus{" +
                "id=" + id +
                ", publicationXPublicationStatus=" + publicationXPublicationStatus +
                ", realStatus=" + realStatus +
                '}';
    }
}
