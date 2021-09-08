package com.ftn.poslovnainformatika.narodnabanka.controller.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.DelatnostDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.impl.poslovnabanka.DelatnostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/delatnosti")
public class DelatnostController {
    @Autowired
    private DelatnostService delatnostService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<DelatnostDTO> getDelatnost(@PathVariable("id") int id){
        DelatnostDTO delatnostDTO = delatnostService.getOne(id);
        return new ResponseEntity<>(delatnostDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Void> updateDelatnost(@PathVariable("id") int id, @RequestBody DelatnostDTO delatnostDTO){
        delatnostService.update(id, delatnostDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteDelatnost(@PathVariable("id") int id){
        delatnostService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Set<DelatnostDTO>> getDelatnosti(){
        Set<DelatnostDTO> delatnosti = delatnostService.getAll();
        return new ResponseEntity<>(delatnosti, HttpStatus.OK);
    }
}
