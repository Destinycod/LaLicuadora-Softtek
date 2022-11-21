package com.lalicuadora.app.presentation;

import com.lalicuadora.app.data.SellerRepository;
import com.lalicuadora.app.domain.models.entities.users.Seller;
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
@RequestMapping(value = "/api/sellers", produces = MediaType.APPLICATION_JSON_VALUE)
public class SellerController {
    @Autowired
    SellerRepository sellerRepository;

    @GetMapping("")
    Page<Seller> sellers(Pageable page) {
        return sellerRepository.findAll(page);
    }

    @GetMapping("/{id}")
    Optional<Seller> findById(@PathVariable(value = "id") Long id){
        return this.sellerRepository.findById(id);
    }

    @PostMapping("")
    ResponseEntity<Seller> save(@RequestBody @Valid Seller seller,
                BindingResult bindingResult) { //throws exception
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Seller>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Seller>(this.sellerRepository.save(seller), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Seller> update(@PathVariable(value = "id") Long id, @RequestBody Seller seller) {
        if(sellerRepository.existsById(id)){
            seller.setId(id);
            try {
                return new ResponseEntity<Seller>(this.sellerRepository.save(seller), HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<Seller>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<Seller>(HttpStatus.NOT_FOUND);
        }
    }
}
