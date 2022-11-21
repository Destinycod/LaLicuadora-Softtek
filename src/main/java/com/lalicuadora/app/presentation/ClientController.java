package com.lalicuadora.app.presentation;

import com.lalicuadora.app.data.ClientRepository;
import com.lalicuadora.app.domain.models.entities.users.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("")
    Page<Client> clients(Pageable page) {
        return clientRepository.findAll(page);
    }

    @GetMapping("/{id}")
    Optional<Client> findById(@PathVariable(value = "id") Long id){
        return this.clientRepository.findById(id);
    }



    @PostMapping("")
    ResponseEntity<Client> save(@RequestBody @Valid Client client,
                       BindingResult bindingResult) { //throws exception
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Client>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Client>(this.clientRepository.save(client), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Client> update(@PathVariable(value = "id") Long id, @RequestBody Client client) {
        if (clientRepository.existsById(id)) {
            client.setId(id);
            try {
                return new ResponseEntity<Client>(this.clientRepository.save(client), HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<Client>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
    }
}
