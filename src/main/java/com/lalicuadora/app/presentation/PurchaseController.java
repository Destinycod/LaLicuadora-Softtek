package com.lalicuadora.app.presentation;

import com.lalicuadora.app.data.PurchaseRepository;
import com.lalicuadora.app.domain.models.entities.shops.Purchase;
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
@RequestMapping(value = "/api/purchases", produces = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseController {

    @Autowired
    PurchaseRepository purchaseRepository;

    @GetMapping("")
    Page<Purchase> purchases(Pageable page) {
        return purchaseRepository.findAll(page);
    }

    @GetMapping("/{id}")
    Optional<Purchase> findById(@PathVariable(value = "id") Long id){
        return this.purchaseRepository.findById(id);
    }

    @PostMapping("")
    ResponseEntity<Purchase> save(@RequestBody @Valid Purchase purchase,
                BindingResult bindingResult) { //throws exception
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Purchase>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Purchase>(this.purchaseRepository.save(purchase), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Purchase> update(@PathVariable(value = "id") Long id, @RequestBody Purchase purchase) {
        if(purchaseRepository.existsById(id)){
            purchase.setId(id);
            try {
                return new ResponseEntity<Purchase>(this.purchaseRepository.save(purchase), HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<Purchase>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<Purchase>(HttpStatus.NOT_FOUND);
        }
    }
}
