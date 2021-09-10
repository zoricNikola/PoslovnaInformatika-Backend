package com.ftn.poslovnainformatika.narodnabanka.controller.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.TekuciRacunDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.impl.poslovnabanka.TekuciRacunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/tekuci-racuni")
public class TekuciRacunController {
    @Autowired
    private TekuciRacunService tekuciRacunService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<TekuciRacunDTO> getTekuciRacun(@PathVariable("id") String id){
        TekuciRacunDTO tekuciRacunDTO = tekuciRacunService.getOne(id);
        return new ResponseEntity<>(tekuciRacunDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> createTekuciRacun(@RequestBody TekuciRacunDTO tekuciRacunDTO){
        tekuciRacunService.createe(tekuciRacunDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTekuciRacun(@PathVariable("id") String id){
        tekuciRacunService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Set<TekuciRacunDTO>> getTekuciRacuni(){
        Set<TekuciRacunDTO> tekuciRacuni = tekuciRacunService.getAll();
        return new ResponseEntity<>(tekuciRacuni, HttpStatus.OK);
    }
}
