package com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tekuci_racun")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TekuciRacun {
	
	@Id
    @Column(name = "broj_racuna", unique = true, nullable = false, length = 18)
    private String brojRacuna;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "klijent", referencedColumnName = "klijent_id", nullable = false)
    private Klijent klijent;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "poslovna_banka", referencedColumnName = "sifra_banke", nullable = false)
    private PoslovnaBanka poslovnaBanka;

}
