package com.ftn.poslovnainformatika.narodnabanka.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "dnevno_stanje")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DnevnoStanje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dnevno_stanje_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "datum", nullable = false)
    private LocalDate datum;

    @Column(name = "prethodno_stanje", precision = 15, scale = 2, nullable = false)
    private double prethodnoStanje;

    @Column(name = "promet_na_teret", precision = 15, scale = 2, nullable = false)
    private double prometNaTeret;

    @Column(name = "promet_u_korist", precision = 15, scale = 2, nullable = false)
    private double prometUKorist;

    @Column(name = "novo_stanje", precision = 15, scale = 2, nullable = false)
    private double novoStanje;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dnevnoStanjeBankeDuznika")
    private Set<Poruka> porukeKaoBankaDuznika;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dnevnoStanjeBankePoverioca")
    private Set<Poruka> porukeKaoBankaPoverioca;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "obracunski_racun", referencedColumnName = "broj_obracunskog_racuna", nullable = false)
    private ObracunskiRacun obracunskiRacun;

}
