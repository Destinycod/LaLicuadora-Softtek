package com.lalicuadora.app.data;

import com.lalicuadora.app.domain.models.entities.shops.PublicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="publicationStatus")
public interface PublicationStatusRepository extends JpaRepository<PublicationStatus, Long> {

}
