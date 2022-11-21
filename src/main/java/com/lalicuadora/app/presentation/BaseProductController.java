package com.lalicuadora.app.presentation;

import com.lalicuadora.app.data.BaseProductRepository;
import com.lalicuadora.app.domain.exceptions.InvalidBaseProductException;
import com.lalicuadora.app.domain.models.entities.products.BaseProduct;
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

//ESTA VISTA SOLO ESTARIA DISPONIBLE PARA EL MANAGER, ADMINISTRA LOS PRODUCTOS BASE

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/baseProducts", produces = MediaType.APPLICATION_JSON_VALUE)
public class BaseProductController {

    @Autowired
    BaseProductRepository baseProductRepository;

    @GetMapping("")
    Page<BaseProduct> baseProducts(
            @RequestParam(value = "price", required = false) Double price,
            Pageable page){
        if(price != null) {
            return baseProductRepository.findByPrice(price, page);
        }
        return baseProductRepository.findAll(page);
    }

    @GetMapping("/{id}")
    Optional<BaseProduct> findById(@PathVariable(value = "id") Long id){
        //if(baseProductRepository.existsById(id)){
            return this.baseProductRepository.findById(id);
        //}

    }

    @PostMapping("")
    ResponseEntity<BaseProduct> save(@RequestBody @Valid BaseProduct baseProduct,
                       BindingResult bindingResult) throws InvalidBaseProductException {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<BaseProduct>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<BaseProduct>(this.baseProductRepository.save(baseProduct), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<BaseProduct> update(@RequestBody BaseProduct baseProduct, @PathVariable(value = "id") Long id) {
        if(baseProductRepository.existsById(id)){
            baseProduct.setId(id);
            try {
                return new ResponseEntity<BaseProduct>(this.baseProductRepository.save(baseProduct), HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<BaseProduct>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else{
            return new ResponseEntity<BaseProduct>(HttpStatus.NOT_FOUND);
        }
    }
}