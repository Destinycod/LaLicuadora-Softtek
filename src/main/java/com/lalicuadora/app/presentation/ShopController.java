package com.lalicuadora.app.presentation;

import com.lalicuadora.app.data.ShopRepository;
import com.lalicuadora.app.domain.models.entities.shops.Shop;
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
@RequestMapping(value = "/api/shops", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShopController {

    @Autowired
    ShopRepository shopRepository;

    @GetMapping("")
    Page<Shop> shops(Pageable page) {
        return shopRepository.findAll(page);
    }

    @GetMapping("/{id}")
    Optional<Shop> findById(@PathVariable(value = "id") Long id){
        return this.shopRepository.findById(id);
    }

    @PostMapping("")
    ResponseEntity<Shop> save(@RequestBody @Valid Shop shop,
                BindingResult bindingResult) { //throws exception
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Shop>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Shop>(this.shopRepository.save(shop), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Shop> update(@PathVariable(value = "id") Long id, @RequestBody Shop shop) {
        if(shopRepository.existsById(id)){
            shop.setId(id);
            try {
                return new ResponseEntity<Shop>(this.shopRepository.save(shop), HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<Shop>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
        }
    }
}
