package com.lalicuadora.app.data;

import com.lalicuadora.app.domain.models.entities.products.BaseProdXPossibleCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path="baseProdXPossibleCustomRepository")
public interface BaseProdXPossibleCustomRepository extends JpaRepository<BaseProdXPossibleCustom, Long> {

    @Override
    @RestResource(exported = false) //evitar la exposicion
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(BaseProdXPossibleCustom id);



}
