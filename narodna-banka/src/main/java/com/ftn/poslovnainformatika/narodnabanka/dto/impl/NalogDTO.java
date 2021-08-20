package com.ftn.poslovnainformatika.narodnabanka.dto.impl;

import java.time.LocalDate;

import com.ftn.poslovnainformatika.narodnabanka.dto.nalog.NalogViewDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NalogDTO implements NalogViewDTO {
	
	private int id;
	
	private String duznik;
	
	private String poverilac;
	
	private String racunDuznika;
	
	private String racunPoverioca;
	
	private String svrhaPlacanja;
	
	private double iznos;
	
	private String sifraValute;
	
	private LocalDate datum;
	
	private boolean hitno;
	
	private String pozivNaBrojZaduzenja;
	
	private String pozivNaBrojOdobrenja;
	
	private Integer modelZaduzenja;
	
	private Integer modelOdobrenja;
	
}
