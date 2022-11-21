package com.lalicuadora.app.presentation;

import com.lalicuadora.app.data.PublicationStatusRepository;
import com.lalicuadora.app.domain.models.entities.shops.PublicationStatus;
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
@RequestMapping(value = "/api/publicationStatus", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicationStatusController {

    @Autowired
    PublicationStatusRepository publicationStatusRepository;

    @GetMapping("")
    Page<PublicationStatus> publicationStatus(Pageable page) {
        return publicationStatusRepository.findAll(page);
    }

    @GetMapping("/{id}")
    Optional<PublicationStatus> findById(@PathVariable(value = "id") Long id){
        return this.publicationStatusRepository.findById(id);
    }

    @PostMapping("")
    ResponseEntity<PublicationStatus> save(@RequestBody @Valid PublicationStatus publicationStatus,
                                           BindingResult bindingResult) { //throws exception
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<PublicationStatus>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<PublicationStatus>(this.publicationStatusRepository.save(publicationStatus), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<PublicationStatus> update(@PathVariable(value = "id") Long id, @RequestBody PublicationStatus publicationStatus) {
        if(publicationStatusRepository.existsById(id)){
            publicationStatus.setId(id);
            try {
                return new ResponseEntity<PublicationStatus>(publicationStatusRepository.save(publicationStatus), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<PublicationStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<PublicationStatus>(HttpStatus.NOT_FOUND);
        }
    }
}
