package com.lalicuadora.app.presentation;

import com.lalicuadora.app.data.CustomProductRepository;
import com.lalicuadora.app.domain.models.entities.products.CustomProduct;
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
@RequestMapping(value = "/api/customProducts", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomProductController {

    @Autowired
    CustomProductRepository customProductRepository;

    @GetMapping("")
    Page<CustomProduct> customProducts(Pageable page){
        return customProductRepository.findAll(page);
    }

    @GetMapping("/{id}")
    Optional<CustomProduct> findById(@PathVariable(value = "id") Long id){
        return this.customProductRepository.findById(id);
    }

    @PostMapping("")
    ResponseEntity<CustomProduct> save(@RequestBody @Valid CustomProduct customProduct,
                BindingResult bindingResult){ //throws exception
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<CustomProduct>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<CustomProduct>(this.customProductRepository.save(customProduct), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<CustomProduct> update(@PathVariable(value = "id") Long id, @RequestBody CustomProduct customProduct) {
        if(customProductRepository.existsById(id)){
            customProduct.setId(id);
            try {
                return new ResponseEntity<CustomProduct>(this.customProductRepository.save(customProduct), HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<CustomProduct>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<CustomProduct>(HttpStatus.NOT_FOUND);
        }
    }
}
