package com.lalicuadora.app.data;

import com.lalicuadora.app.domain.models.entities.shops.PublicationXPublicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path="publicationXPublicationStatus")
public interface PublicationXPublicationStatusRepository extends JpaRepository<PublicationXPublicationStatus, Long> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(PublicationXPublicationStatus id);
}
