package com.lalicuadora.app.presentation;

import com.lalicuadora.app.data.SpecificCustomizationRepository;
import com.lalicuadora.app.domain.models.entities.products.SpecificCustomization;
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
@RequestMapping(value = "/api/specificCustomizations", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpecificCustomizationController {

    @Autowired
    SpecificCustomizationRepository specificCustomizationRepository;

    @GetMapping("")
    Page<SpecificCustomization> specificCustomizations(Pageable page){
        return specificCustomizationRepository.findAll(page);
    }

    @GetMapping("/{id}")
    Optional<SpecificCustomization> findById(@PathVariable(value = "id") Long id){
        return this.specificCustomizationRepository.findById(id);
    }

    @PostMapping("")
    ResponseEntity<SpecificCustomization> save(@RequestBody @Valid SpecificCustomization specificCustomization,
                BindingResult bindingResult){ //throws exception
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<SpecificCustomization>(HttpStatus.BAD_REQUEST);
        }
        Double basePrice = specificCustomization.getBaseProdXPossibleCustom().getBaseProduct().getPrice();
        System.out.println("base Product price:" + basePrice);
        Double customPrice = specificCustomization.getCustomizations().getPrice();
        specificCustomization.setPrice(basePrice+customPrice);
        System.out.println("specific price:" + specificCustomization.getPrice());
        return new ResponseEntity<SpecificCustomization>(this.specificCustomizationRepository.save(specificCustomization), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<SpecificCustomization> update(@PathVariable(value = "id") Long id, @RequestBody SpecificCustomization specificCustomization) {
        if(specificCustomizationRepository.existsById(id)){
            specificCustomization.setId(id);
            try {
                return new ResponseEntity<SpecificCustomization>(this.specificCustomizationRepository.save(specificCustomization), HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<SpecificCustomization>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<SpecificCustomization>(HttpStatus.NOT_FOUND);
        }
    }
}
