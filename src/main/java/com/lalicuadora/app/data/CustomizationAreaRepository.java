package com.lalicuadora.app.data;

import com.lalicuadora.app.domain.models.entities.products.CustomizationArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path="posibleCustomization")
public interface CustomizationAreaRepository extends JpaRepository<CustomizationArea, Long> {

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(CustomizationArea id);

}
