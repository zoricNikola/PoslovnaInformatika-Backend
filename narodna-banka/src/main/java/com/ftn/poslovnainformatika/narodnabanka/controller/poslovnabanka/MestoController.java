package com.ftn.poslovnainformatika.narodnabanka.controller.poslovnabanka;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.MestoDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka.MestoService;

@RestController
@RequestMapping("/api/mesta")
public class MestoController {
	
	@Autowired
	private MestoService mestoService;
	
	@GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<MestoDTO> getMesto(@PathVariable("id") int id) {
		MestoDTO mesto = mestoService.getOne(id);
        return new ResponseEntity<>(mesto, HttpStatus.OK);
    }
	
	@GetMapping
    public ResponseEntity<Set<MestoDTO>> getAll() {
        Set<MestoDTO> mesta = mestoService.getAll();
        return new ResponseEntity<>(mesta, HttpStatus.OK);
    }

}
