package com.ftn.poslovnainformatika.narodnabanka.dto.impl;

import java.time.LocalDate;
import java.util.Set;

import com.ftn.poslovnainformatika.narodnabanka.dto.nalog.NalogViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poruka.PorukaViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.poslovnabanka.PoslovnaBankaViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.VrstaPoruke;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PorukaDTO implements PorukaViewDTO {
	
	private int id;

	private LocalDate datum;
	
	private VrstaPoruke vrstaPoruke;
	
	private double ukupanIznos;
	
	private String sifraValute;
	
	private LocalDate datumValute;
	
	private PoslovnaBankaViewDTO bankaDuznika;
	
	private PoslovnaBankaViewDTO bankaPoverioca;

	private Set<NalogViewDTO> nalozi;

}
