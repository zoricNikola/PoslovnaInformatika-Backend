package com.ftn.poslovnainformatika.narodnabanka.dto;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NalogDTO {
	
	private Integer id;
	
	private String duznik;
	
	private String poverilac;
	
	private String racunDuznika;
	
	private String racunPoverioca;
	
	private String svrhaPlacanja;
	
	private Double iznos;
	
	private String sifraValute;
	
	private LocalDate datum;
	
	private boolean hitno;
	
	private String pozivNaBrojZaduzenja;
	
	private String pozivNaBrojOdobrenja;
	
	private Integer modelZaduzenja;
	
	private Integer modelOdobrenja;
	
}
