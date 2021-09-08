package com.ftn.poslovnainformatika.narodnabanka.controller;

import com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji.IzvodObracunskogRacunaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji.StanjeObracunskogRacunaDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.ObracunskiRacunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/obracunski-racuni")
public class ObracunskiRacunController {

    @Autowired
    private ObracunskiRacunService obracunskiRacunService;

    @GetMapping(value = "/stanje")
    public ResponseEntity<Set<StanjeObracunskogRacunaDTO>> getStanjaObracunskihRacuna(@RequestParam(name = "datum") String datumString) {
        LocalDate datum = null;
        try {
            datum = LocalDate.parse(datumString);
        } catch (Exception e) {}

        Set<StanjeObracunskogRacunaDTO> stanja = obracunskiRacunService.getStanjaObracunskihRacuna(datum);

        return new ResponseEntity<>(stanja, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/izvod")
    public ResponseEntity<IzvodObracunskogRacunaDTO> getIzvodObracunskogRacuna(@PathParam("id") int bankaId,
                                                                                     @RequestParam("startDatum") String startDatumStr,
                                                                                     @RequestParam("endDatum") String endDatumStr) {
        LocalDate startDatum = null;
        LocalDate endDatum = null;
        try {
            startDatum = LocalDate.parse(startDatumStr);
        } catch (Exception e) {}
        try {
            endDatum = LocalDate.parse(endDatumStr);
        } catch (Exception e) {}

        IzvodObracunskogRacunaDTO izvod = obracunskiRacunService.getIzvodObracunskogRacuna(bankaId, startDatum, endDatum);

        return new ResponseEntity<>(izvod, HttpStatus.OK);
    }
}
