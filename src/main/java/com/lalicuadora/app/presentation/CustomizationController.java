package com.lalicuadora.app.presentation;

import com.lalicuadora.app.data.CustomizationRepository;
import com.lalicuadora.app.domain.models.entities.products.Customization;
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

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/customizations", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomizationController {

    @Autowired
    CustomizationRepository customizationRepository;

    @GetMapping("")
    Page<Customization> customizations(
            @RequestParam(value = "price", required = false) Double price,
            Pageable page) {
        if(price != null) {
            return customizationRepository.findByPrice(price, page);
        }
        return customizationRepository.findAll(page);
    }

    @GetMapping("/{name}")
    Page<Customization> customization(@PathVariable(value = "name") String name,
                                      @RequestParam(value = "content", required = false) String content,
                                      Pageable page){
        if(content != null){
            return customizationRepository.findByContent(content, page);
        }
        return customizationRepository.findByName(name, page);
    }

    @PostMapping("")
    @Valid
    ResponseEntity<Customization> save(@RequestBody @Valid Customization customization,
                BindingResult bindingResult) { //throws exception
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Customization>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Customization>(this.customizationRepository.save(customization), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Customization> update(@PathVariable(value = "id") Long id, @RequestBody Customization customization) {
        if(customizationRepository.existsById(id)){
            customization.setId(id);
            try {
                return new ResponseEntity<Customization>(customizationRepository.save(customization), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
