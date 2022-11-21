package com.lalicuadora.app.presentation;

import com.lalicuadora.app.data.BaseProdXPossibleCustomRepository;
import com.lalicuadora.app.domain.models.entities.products.BaseProdXPossibleCustom;
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
@RequestMapping(value = "/api/baseProdXPossibleCustoms", produces = MediaType.APPLICATION_JSON_VALUE)
public class BaseProdXPossibleCustomController {

    @Autowired
    BaseProdXPossibleCustomRepository baseProdXPossibleCustomRepository;

    @GetMapping("")
    Page<BaseProdXPossibleCustom> baseProdXPossibleCustoms(Pageable page){
        return baseProdXPossibleCustomRepository.findAll(page);
    }

    @GetMapping("/{id}")
    Optional<BaseProdXPossibleCustom> findById(@PathVariable(value = "id") Long id){
        return this.baseProdXPossibleCustomRepository.findById(id);
    }

    @PostMapping("")
    ResponseEntity<BaseProdXPossibleCustom> save(@RequestBody @Valid BaseProdXPossibleCustom baseProdXPossibleCustom,
                BindingResult bindingResult){ //throws exception
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<BaseProdXPossibleCustom>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<BaseProdXPossibleCustom>(this.baseProdXPossibleCustomRepository.save(baseProdXPossibleCustom), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<BaseProdXPossibleCustom> update(@PathVariable(value = "id") Long id, @RequestBody BaseProdXPossibleCustom baseProdXPossibleCustom) {
        if(baseProdXPossibleCustomRepository.existsById(id)){
            baseProdXPossibleCustom.setId(id);
            try {
                return new ResponseEntity<BaseProdXPossibleCustom>(this.baseProdXPossibleCustomRepository.save(baseProdXPossibleCustom), HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<BaseProdXPossibleCustom>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<BaseProdXPossibleCustom>(HttpStatus.NOT_FOUND);
        }
    }
}
