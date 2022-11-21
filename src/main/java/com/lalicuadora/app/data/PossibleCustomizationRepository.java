package com.lalicuadora.app.data;

import com.lalicuadora.app.domain.models.entities.products.PossibleCustomization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path="posibleCustomization")
public interface PossibleCustomizationRepository extends JpaRepository<PossibleCustomization, Long> {

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(PossibleCustomization id);

    //TODO
    //Page<PossibleCustomization> findByName(String name, Pageable page);


}
