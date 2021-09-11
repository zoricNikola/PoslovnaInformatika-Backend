package com.ftn.poslovnainformatika.narodnabanka.controller.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji.IzvodObracunskogRacunaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.TekuciRacunDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka.PoslovnaBankaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

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

    @GetMapping
    public ResponseEntity<Set<PoslovnaBankaDTO>> getAll() {
        Set<PoslovnaBankaDTO> banke = poslovnaBankaService.getAll();
        return new ResponseEntity<>(banke, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{sifraBanke}/klijenti", produces = "application/json")
    public ResponseEntity<Set<KlijentDTO>> getPoslovnaBankaKlijenti(@PathVariable("sifraBanke") int sifraBanke) {
    	Set<KlijentDTO> klijenti = poslovnaBankaService.getPoslovnaBankaKlijenti(sifraBanke);
    	return new ResponseEntity<>(klijenti, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{sifraBanke}/tekuciRacuni", produces = "application/json")
    public ResponseEntity<Set<TekuciRacunDTO>> getPoslovnaBankaRacuni(@PathVariable("sifraBanke") int sifraBanke) {
    	Set<TekuciRacunDTO> racuni = poslovnaBankaService.getPoslovnaBankaRacuni(sifraBanke);
    	return new ResponseEntity<>(racuni, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{sifraBanke}/izvod")
    public ResponseEntity<IzvodObracunskogRacunaDTO> getIzvodObracunskogRacuna(@PathVariable("sifraBanke") int sifraBanke,
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

        IzvodObracunskogRacunaDTO izvod = poslovnaBankaService.getIzvodObracunskogRacuna(sifraBanke, startDatum, endDatum);

        return new ResponseEntity<>(izvod, HttpStatus.OK);
    }

}
