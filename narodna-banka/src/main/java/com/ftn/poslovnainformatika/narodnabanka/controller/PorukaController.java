package com.ftn.poslovnainformatika.narodnabanka.controller;

import com.ftn.poslovnainformatika.narodnabanka.dto.NalogDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.PorukaDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.PorukaService;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/poruke")
public class PorukaController {

	@Autowired
	private PorukaService porukaService;
	
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> receivePorukaDTO(@RequestBody PorukaDTO porukaDTO) {
        System.out.println("PORUKA DOBAVLJENA IZ SERVISA POSLOVNE BANKE " + porukaDTO.toString());
        
        porukaService.create(porukaDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping(path = "/{id}/nalozi", produces = "application/json")
    public ResponseEntity<Set<NalogDTO>> getNaloziPoruke(@PathVariable("id") int id) {
    	Set<NalogDTO> nalozi = porukaService.getNaloziPoruke(id);
    	
    	return new ResponseEntity<Set<NalogDTO>>(nalozi, HttpStatus.OK);
    }
}
