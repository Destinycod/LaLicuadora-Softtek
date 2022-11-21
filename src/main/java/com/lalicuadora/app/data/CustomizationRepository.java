package com.lalicuadora.app.data;

import com.lalicuadora.app.domain.models.entities.products.Customization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path="customizations")
public interface CustomizationRepository extends JpaRepository<Customization, Long> {

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(Customization id);

    Page<Customization> findByName(String name, Pageable page);

    Page<Customization> findByContent(String content, Pageable page);

    Page<Customization> findByPrice(Double basePrice, Pageable page);
}
// TODO