package com.lalicuadora.app.data;

import com.lalicuadora.app.domain.models.entities.shops.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="purchases")
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
