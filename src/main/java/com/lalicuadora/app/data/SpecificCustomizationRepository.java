package com.lalicuadora.app.data;

import com.lalicuadora.app.domain.models.entities.products.SpecificCustomization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path="customization")
public interface SpecificCustomizationRepository extends JpaRepository<SpecificCustomization, Long> {

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(SpecificCustomization id);
}
