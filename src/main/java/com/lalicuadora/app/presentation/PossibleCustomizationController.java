package com.lalicuadora.app.presentation;

import com.lalicuadora.app.data.PossibleCustomizationRepository;
import com.lalicuadora.app.domain.models.entities.products.PossibleCustomization;
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

//ESTA VISTA SOLO ESTARIA DISPONIBLE PARA EL SELLER, ADMINISTRA LAS PERSONALIZACIONES POSIBLES

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/possibleCustomizations", produces = MediaType.APPLICATION_JSON_VALUE)
public class PossibleCustomizationController {

    @Autowired
    PossibleCustomizationRepository possibleCustomizationRepository;

    @GetMapping("")
    Page<PossibleCustomization> posibleCustomizations(Pageable page){
        return possibleCustomizationRepository.findAll(page);
    }

    @GetMapping("/{id}")
    Optional<PossibleCustomization> findById(@PathVariable(value = "id") Long id){
        return this.possibleCustomizationRepository.findById(id);
    }

    @PostMapping("")
    ResponseEntity<PossibleCustomization> save(@RequestBody @Valid PossibleCustomization possibleCustomization,
                BindingResult bindingResult){ //throws exception
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<PossibleCustomization>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<PossibleCustomization>(this.possibleCustomizationRepository.save(possibleCustomization), HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<PossibleCustomization> update(@PathVariable(value = "id") Long id, @RequestBody PossibleCustomization possibleCustomization) {
        if(possibleCustomizationRepository.existsById(id)){
            possibleCustomization.setId(id);
            try {
                return new ResponseEntity<PossibleCustomization>(this.possibleCustomizationRepository.save(possibleCustomization), HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<PossibleCustomization>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<PossibleCustomization>(HttpStatus.NOT_FOUND);
        }
    }

}