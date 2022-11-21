package com.lalicuadora.app.data;

import com.lalicuadora.app.domain.models.entities.users.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="sellers")
public interface SellerRepository extends JpaRepository<Seller, Long> {

}
