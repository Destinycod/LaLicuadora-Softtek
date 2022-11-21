package com.lalicuadora.app.data;

import com.lalicuadora.app.domain.models.entities.shops.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path="cart")
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(Cart id);
}
