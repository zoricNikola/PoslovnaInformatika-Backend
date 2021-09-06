package com.ftn.poslovnainformatika.narodnabanka.controller;

import com.ftn.poslovnainformatika.narodnabanka.dto.NalogDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.NalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/nalozi")
public class NalogController {

    @Autowired
    private NalogService nalogService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Set<NalogDTO>> getNaloziPoruke (@PathVariable("id") int porukaId) {
        Set<NalogDTO> naloziPoruke = nalogService.getByPorukaId(porukaId);

        return new ResponseEntity<>(naloziPoruke, HttpStatus.OK);
    }
}
