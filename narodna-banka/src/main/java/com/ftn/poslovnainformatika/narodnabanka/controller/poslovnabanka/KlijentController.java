package com.ftn.poslovnainformatika.narodnabanka.controller.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.impl.poslovnabanka.KlijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/klijenti")
public class KlijentController {
    @Autowired
    private KlijentService klijentService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<KlijentDTO> getKlijent(@PathVariable("id") int id){
        KlijentDTO klijentDTO = klijentService.getOne(id);
        return new ResponseEntity<>(klijentDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> createKlijent(@RequestBody KlijentDTO klijentDTO){
        klijentService.create(klijentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Void> updateKlijent(@PathVariable("id") int id, @RequestBody KlijentDTO klijentDTO){
        klijentService.update(id, klijentDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteKlijent(@PathVariable("id") int id){
        klijentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Set<KlijentDTO>> getKlijenti(){
        Set<KlijentDTO> klijenti = klijentService.getAll();
        return new ResponseEntity<>(klijenti, HttpStatus.OK);
    }
}
