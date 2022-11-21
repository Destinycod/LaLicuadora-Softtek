package com.lalicuadora.app.presentation;

import com.lalicuadora.app.data.ItemRepository;
import com.lalicuadora.app.domain.models.entities.shops.Item;
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
@RequestMapping(value = "/api/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("")
    Page<Item> items(Pageable page) {
        return itemRepository.findAll(page);
    }

    @GetMapping("/{id}")
    Optional<Item> findById(@PathVariable(value = "id") Long id){
        return this.itemRepository.findById(id);
    }

    @PostMapping("")
    ResponseEntity<Item> save(@RequestBody @Valid Item item,
                BindingResult bindingResult) { //throws exception
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Item>(HttpStatus.BAD_REQUEST);
        }
        Double productPrice = item.getCustomProduct().getSpecificCustomization().getPrice();
        item.setPrice(productPrice * item.getAmount());
        return new ResponseEntity<Item>(this.itemRepository.save(item), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Item> update(@PathVariable(value = "id") Long id, @RequestBody Item item) {
        if(itemRepository.existsById(id)){
            item.setId(id);
            try {
                return new ResponseEntity<Item>(this.itemRepository.save(item), HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<Item>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
        }
    }
}
