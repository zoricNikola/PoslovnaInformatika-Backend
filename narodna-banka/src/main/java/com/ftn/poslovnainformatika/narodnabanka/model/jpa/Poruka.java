package com.ftn.poslovnainformatika.narodnabanka.model.jpa;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.PoslovnaBanka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "poruka")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Poruka {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "poruka_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "datum", nullable = false)
	private LocalDate datum;
	
	@Column(name = "vrsta_poruke", nullable = false)
	@Enumerated(EnumType.STRING)
	private VrstaPoruke vrstaPoruke;
	
	@Column(name = "ukupan_iznos", nullable = false)
	private double ukupanIznos;
	
	@Column(name = "sifra_valute", nullable = false, length = 3)
	private String sifraValute;
	
	@Column(name = "datum_valute", nullable = false)
	private LocalDate datumValute;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "banka_duznika_id", referencedColumnName = "poslovna_banka_id", nullable = false)
	private PoslovnaBanka bankaDuznika;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "banka_poverioca_id", referencedColumnName = "poslovna_banka_id", nullable = false)
	private PoslovnaBanka bankaPoverioca;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "dnevno_stanje_id", referencedColumnName = "dnevno_stanje_id", nullable = false)
	private DnevnoStanje dnevnoStanje;
	
	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "poruka")
	private Set<Nalog> nalozi;
	
}