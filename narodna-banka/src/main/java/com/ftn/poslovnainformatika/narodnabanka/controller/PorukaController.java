package com.ftn.poslovnainformatika.narodnabanka.controller;

import com.ftn.poslovnainformatika.narodnabanka.dto.PorukaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/poruke")
public class PorukaController {

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> testReceivePorukaDTO(@RequestBody PorukaDTO porukaDTO) {
        System.out.println("PORUKA DOBAVLJENA IZ SERVISA POSLOVNE BANKE " + porukaDTO.toString());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
