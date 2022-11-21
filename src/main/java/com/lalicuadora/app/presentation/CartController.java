package com.lalicuadora.app.presentation;

import com.lalicuadora.app.data.CartRepository;
import com.lalicuadora.app.domain.models.entities.shops.Cart;
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
@RequestMapping(value = "/api/cart", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartController {

    @Autowired
    CartRepository cartRepository;

    @GetMapping("")
    Page<Cart> carts(Pageable page) {
        return cartRepository.findAll(page);
    }

    @GetMapping("/{id}")
    Optional<Cart> findById(@PathVariable(value = "id") Long id){
        return this.cartRepository.findById(id);
    }

    @PostMapping("")
    ResponseEntity<Cart> save(@RequestBody @Valid Cart cart,
                BindingResult bindingResult) { //throws exception
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Cart>(HttpStatus.BAD_REQUEST);
        }
        Double totalPrice = cart.getItem().stream().mapToDouble(
                    item -> item.getPrice() == null ? 0 : item.getPrice()
            ).sum();
        cart.setTotalPrice(totalPrice);
        return new ResponseEntity<Cart>(this.cartRepository.save(cart), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Cart> update(@PathVariable(value = "id") Long id, @RequestBody Cart cart) {
        if (cartRepository.existsById(id)) {
            cart.setId(id);
            try {
                return new ResponseEntity<Cart>(this.cartRepository.save(cart), HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<Cart>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
        }
    }
}
