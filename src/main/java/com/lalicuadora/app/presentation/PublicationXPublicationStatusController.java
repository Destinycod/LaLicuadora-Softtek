package com.lalicuadora.app.presentation;

import com.lalicuadora.app.data.PublicationXPublicationStatusRepository;
import com.lalicuadora.app.domain.models.entities.shops.PublicationXPublicationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/publicationXPublicationStatus", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicationXPublicationStatusController {

    @Autowired
    PublicationXPublicationStatusRepository publicationXPublicationStatusRepository;

    @GetMapping("")
    Page<PublicationXPublicationStatus> publicationXPublicationStatus(Pageable page) {
        return publicationXPublicationStatusRepository.findAll(page);
    }

    @PostMapping("")
    @Valid
    ResponseEntity<PublicationXPublicationStatus> save(@RequestBody @Valid PublicationXPublicationStatus publicationXPublicationStatus,
                                                       BindingResult bindingResult) { //throws exception
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<PublicationXPublicationStatus>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<PublicationXPublicationStatus>(this.publicationXPublicationStatusRepository.save(publicationXPublicationStatus), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<PublicationXPublicationStatus> update(@PathVariable(value = "id") Long id, @RequestBody PublicationXPublicationStatus publicationXPublicationStatus) {
        if(publicationXPublicationStatusRepository.existsById(id)){
            publicationXPublicationStatus.setId(id);
            try {
                return new ResponseEntity<PublicationXPublicationStatus>(publicationXPublicationStatusRepository.save(publicationXPublicationStatus), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<PublicationXPublicationStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<PublicationXPublicationStatus>(HttpStatus.NOT_FOUND);
        }
    }
}
