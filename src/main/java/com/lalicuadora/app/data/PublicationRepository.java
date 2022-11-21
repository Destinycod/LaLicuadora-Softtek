package com.lalicuadora.app.data;

import com.lalicuadora.app.domain.models.entities.shops.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="publications")
public interface PublicationRepository extends JpaRepository<Publication, Long> {

}
