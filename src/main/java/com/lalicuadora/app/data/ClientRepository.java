package com.lalicuadora.app.data;

import com.lalicuadora.app.domain.models.entities.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path="clients")
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(Client id);


}
