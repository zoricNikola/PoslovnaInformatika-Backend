package com.ftn.poslovnainformatika.narodnabanka.controller.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka.PoslovnaBankaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/poslovne-banke")
public class PoslovnaBankaController {

    @Autowired
    private PoslovnaBankaService poslovnaBankaService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> createPoslovnaBanka(@RequestBody PoslovnaBankaDTO poslovnaBanka) {
        int id = poslovnaBankaService.create(poslovnaBanka);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Void> updatePoslovnaBanka(@PathVariable("id") int id, @RequestBody PoslovnaBankaDTO poslovnaBanka) {
        poslovnaBankaService.update(id, poslovnaBanka);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePoslovnaBanka(@PathVariable("id") int id) {
        poslovnaBankaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PoslovnaBankaDTO> getPoslovnaBanka(@PathVariable("id") int id) {
        PoslovnaBankaDTO dto = poslovnaBankaService.getOne(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
