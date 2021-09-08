package com.ftn.poslovnainformatika.narodnabanka.controller;

import com.ftn.poslovnainformatika.narodnabanka.dto.ObavestenjeDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.impl.ObavestenjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/obavestenja")
public class ObavestenjeController {
    @Autowired
    private ObavestenjeService obavestenjeService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ObavestenjeDTO> getObavestenje(@PathVariable("id") int id){
        ObavestenjeDTO obavestenjeDTO = obavestenjeService.getOne(id);
        return new ResponseEntity<>(obavestenjeDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Void> updateObavestenje(@PathVariable("id") int id, @RequestBody ObavestenjeDTO obavestenjeDTO){
        obavestenjeService.update(id, obavestenjeDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteObavestenje(@PathVariable("id") int id){
        obavestenjeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Set<ObavestenjeDTO>> getObavestenja(){
        Set<ObavestenjeDTO> obavestenja = obavestenjeService.getAll();
        return new ResponseEntity<>(obavestenja, HttpStatus.OK);
    }
}
