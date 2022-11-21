package com.lalicuadora.app.domain.models.entities.shops;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "publication_x_publication_status")
@Getter
@Setter
public class PublicationXPublicationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "publication_status", referencedColumnName = "id")
    private PublicationStatus publicationStatus;

    @ManyToOne()
    @JoinColumn(name = "publication", referencedColumnName = "id")
    private Publication publication;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "hour")
    private LocalTime hour;

    public PublicationXPublicationStatus(){

    }

    public PublicationXPublicationStatus(Publication publication, PublicationStatus publicationStatus, LocalDate date, LocalTime hour){
        this();
        this.publication = publication;
        this.publicationStatus = publicationStatus;
        this.date = date;
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "PublicationXPublicationStatus{" +
                "id=" + id +
                ", publicationStatus=" + publicationStatus +
                ", publication=" + publication +
                ", date=" + date +
                ", hour=" + hour +
                '}';
    }
}
