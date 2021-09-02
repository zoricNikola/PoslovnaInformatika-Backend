package com.ftn.poslovnainformatika.narodnabanka.model.jpa;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "nalog")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Nalog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nalog_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "duznik", nullable = false)
	private String duznik;
	
	@Column(name = "poverilac", nullable = false)
	private String poverilac;
	
	@Column(name = "racun_duznika", nullable = false, length = 18)
	private String racunDuznika;
	
	@Column(name = "racun_poverioca", nullable = false, length = 18)
	private String racunPoverioca;
	
	@Column(name = "svrha_placanja", nullable = false)
	private String svrhaPlacanja;
	
	@Column(name = "iznos", nullable = false, precision = 15, scale = 2)
	private double iznos;
	
	@Column(name = "sifra_valute", nullable = false, length = 3)
	private String sifraValute;
	
	@Column(name = "datum", nullable = false)
	private LocalDate datum;
	
	@Column(name = "hitno")
	private boolean hitno;
	
	@Column(name = "poziv_na_broj_zaduzenja", length = 20)
	private String pozivNaBrojZaduzenja;
	
	@Column(name = "poziv_na_broj_odobrenja", length = 20)
	private String pozivNaBrojOdobrenja;
	
	@Column(name = "model_zaduzenja", precision = 2)
	private Integer modelZaduzenja;
	
	@Column(name = "model_odobrenja", precision = 2)
	private Integer modelOdobrenja;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "poruka_id", referencedColumnName = "poruka_id", nullable = false)
	private Poruka poruka;

}
