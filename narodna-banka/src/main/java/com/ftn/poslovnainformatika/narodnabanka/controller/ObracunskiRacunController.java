package com.ftn.poslovnainformatika.narodnabanka.controller;

import com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji.StanjeObracunskogRacunaDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.ObracunskiRacunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/obracunski-racuni")
public class ObracunskiRacunController {

    @Autowired
    private ObracunskiRacunService obracunskiRacunService;

    @GetMapping(value = "/stanje")
    public ResponseEntity<List<StanjeObracunskogRacunaDTO>> getStanjaObracunskihRacuna(@RequestParam(name = "datum") String datumString) {
        LocalDate datum = null;
        try {
            datum = LocalDate.parse(datumString);
        } catch (Exception e) {}

        List<StanjeObracunskogRacunaDTO> stanja = obracunskiRacunService.getStanjaObracunskihRacuna(datum);

        return new ResponseEntity<>(stanja, HttpStatus.OK);
    }
}
