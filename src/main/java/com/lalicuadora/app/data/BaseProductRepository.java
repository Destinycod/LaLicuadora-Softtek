package com.lalicuadora.app.data;

import com.lalicuadora.app.domain.models.entities.products.BaseProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path="baseProduct")
public interface BaseProductRepository extends JpaRepository<BaseProduct, Long>{

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(BaseProduct id);

    Page<BaseProduct> findByPrice(Double price, Pageable page);

}
