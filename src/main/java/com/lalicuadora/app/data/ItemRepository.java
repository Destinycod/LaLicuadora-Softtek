package com.lalicuadora.app.data;

import com.lalicuadora.app.domain.models.entities.shops.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="items")
public interface ItemRepository extends JpaRepository<Item, Long> {

}
