package com.ftn.poslovnainformatika.narodnabanka.model.jpa;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Klijent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sifarnik_delatnosti")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SifarnikDelatnosti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifarnik_delatnosti_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "sifra_delatnosti")
    private double sifraDelatnosti;

    @Column(name = "naziv_delatnosti", nullable = false)
    private String nazivDelatnosti;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "klijent")
    private Klijent klijent;
}
