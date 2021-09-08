package com.ftn.poslovnainformatika.poslovnabanka.controller;

import com.ftn.poslovnainformatika.poslovnabanka.dto.IzvodObracunskogRacunaDTO;
import com.ftn.poslovnainformatika.poslovnabanka.dto.ObavestenjeDTO;
import com.ftn.poslovnainformatika.poslovnabanka.dto.PorukaDTO;
import com.ftn.poslovnainformatika.poslovnabanka.service.impl.PoslovnaBankaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PoslovnaBankaController {

    @Autowired
    private PoslovnaBankaService poslovnaBankaService;

    @GetMapping(value = "/sendRTGS")
    public ResponseEntity<Void> sendRTGS() {
        poslovnaBankaService.sendRTGS();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/sendCLEARING")
    public ResponseEntity<Void> sendCLEARING() {
        poslovnaBankaService.sendClearing();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/receive/message", consumes = "application/json")
    public ResponseEntity<Void> receivePoruka(@RequestBody PorukaDTO porukaDTO) {
        poslovnaBankaService.receivePorukaDTO(porukaDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/receive/notification", consumes = "application/json")
    public ResponseEntity<Void> receiveObavestenje(@RequestBody ObavestenjeDTO obavestenjeDTO) {
        poslovnaBankaService.receiveObavestenjeDTO(obavestenjeDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/receive/izvod/{id}", consumes = "application/json")
    public ResponseEntity<Void> receiveIzvod(@PathVariable("id") int bankaId, @RequestBody IzvodObracunskogRacunaDTO izvodDTO) {
        poslovnaBankaService.receiveIzvodObracunskogRacunaDTO(bankaId, izvodDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
