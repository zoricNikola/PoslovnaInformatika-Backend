package com.ftn.poslovnainformatika.narodnabanka.controller;

import com.ftn.poslovnainformatika.narodnabanka.dto.DnevnoStanjeDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.PorukaDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.DnevnoStanjeService;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dnevna-stanja")
public class DnevnoStanjeController {

    @Autowired
    private DnevnoStanjeService dnevnoStanjeService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<DnevnoStanjeDTO> getDnevnoStanje(@PathVariable("id") int id) {
        DnevnoStanjeDTO dnevnoStanjeDTO = dnevnoStanjeService.getOne(id);
        return new ResponseEntity<>(dnevnoStanjeDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Void> updateDnevnoStanje(@PathVariable("id") int id, @RequestBody DnevnoStanjeDTO dnevnoStanje) {
        dnevnoStanjeService.update(id, dnevnoStanje);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteDnevnoStanje(@PathVariable("id") int id) {
        dnevnoStanjeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping(value = "/{id}/poruke", produces = "application/json")
    public ResponseEntity<Set<PorukaDTO>> getPorukeStanja(@PathVariable("id") int id) {
        Set<PorukaDTO> poruke = dnevnoStanjeService.getPorukeStanja(id);
        
        return new ResponseEntity<>(poruke, HttpStatus.OK);
    }
}
