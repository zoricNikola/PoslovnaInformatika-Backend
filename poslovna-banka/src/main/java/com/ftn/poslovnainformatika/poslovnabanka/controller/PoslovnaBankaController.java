package com.ftn.poslovnainformatika.poslovnabanka.controller;

import com.ftn.poslovnainformatika.poslovnabanka.service.PoslovnaBankaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class PoslovnaBankaController {

    @Autowired
    private PoslovnaBankaService poslovnaBankaService;

    @GetMapping(value = "/sendRTGS")
    public ResponseEntity<Void> sendRTGS(){
        poslovnaBankaService.sendRTGS();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
