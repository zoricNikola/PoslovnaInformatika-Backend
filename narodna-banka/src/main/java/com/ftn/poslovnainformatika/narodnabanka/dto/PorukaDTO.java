package com.ftn.poslovnainformatika.narodnabanka.dto;

import java.time.LocalDate;
import java.util.Set;

import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.VrstaPoruke;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PorukaDTO {
	
	private Integer id;

	private LocalDate datum;
	
	private VrstaPoruke vrstaPoruke;
	
	private Double ukupanIznos;
	
	private String sifraValute;
	
	private LocalDate datumValute;
	
	private PoslovnaBankaDTO bankaDuznika;
	
	private PoslovnaBankaDTO bankaPoverioca;
	
	private DnevnoStanjeDTO dnevnoStanje;

	private Set<NalogDTO> nalozi;

}
