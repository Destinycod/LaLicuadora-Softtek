package com.lalicuadora.app.presentation;

import com.lalicuadora.app.data.CustomizationAreaRepository;
import com.lalicuadora.app.domain.models.entities.products.CustomizationArea;
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
@RequestMapping(value = "/api/customizationAreas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomizationAreaController {

    @Autowired
    CustomizationAreaRepository customizationAreaRepository;

    @GetMapping("")
    Page<CustomizationArea> customizationAreas(Pageable page){
        return customizationAreaRepository.findAll(page);
    }

    @GetMapping("/{id}")
    Optional<CustomizationArea> findById(@PathVariable(value = "id") Long id){
        return this.customizationAreaRepository.findById(id);
    }

    @PostMapping("")
    ResponseEntity<CustomizationArea> save(@RequestBody @Valid CustomizationArea customizationArea,
                BindingResult bindingResult){ //throws exception
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<CustomizationArea>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<CustomizationArea>(this.customizationAreaRepository.save(customizationArea), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<CustomizationArea> update(@PathVariable(value = "id") Long id, @RequestBody CustomizationArea customizationArea) {
        if(customizationAreaRepository.existsById(id)){
            customizationArea.setId(id);
            try {
                return new ResponseEntity<CustomizationArea>(this.customizationAreaRepository.save(customizationArea), HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<CustomizationArea>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<CustomizationArea>(HttpStatus.NOT_FOUND);
        }
    }
}
