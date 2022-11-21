package com.lalicuadora.app.presentation;

import com.lalicuadora.app.data.PublicationRepository;
import com.lalicuadora.app.domain.models.entities.shops.Publication;
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
@RequestMapping(value = "/api/publications", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicationController {

    @Autowired
    PublicationRepository publicationRepository;

    @GetMapping("")
    Page<Publication> publications(Pageable page) {
        return publicationRepository.findAll(page);
    }

    @GetMapping("/{id}")
    Optional<Publication> findById(@PathVariable(value = "id") Long id){
        return this.publicationRepository.findById(id);
    }

    @PostMapping("")
    ResponseEntity<Publication> save(@RequestBody @Valid Publication publication,
                BindingResult bindingResult) { //throws exception
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Publication>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Publication>(this.publicationRepository.save(publication), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Publication> update(@PathVariable(value = "id") Long id, @RequestBody Publication publication) {
        if(publicationRepository.existsById(id)){
            publication.setId(id);
            try {
                return new ResponseEntity<Publication>(this.publicationRepository.save(publication), HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<Publication>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<Publication>(HttpStatus.NOT_FOUND);
        }
    }
}
