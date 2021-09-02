package com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "delatnost")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Delatnost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delatnost_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "sifra_delatnosti", nullable = false)
    private Integer sifraDelatnosti;

    @Column(name = "naziv_delatnosti", nullable = false)
    private String nazivDelatnosti;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "delatnost")
    private Set<Klijent> klijenti;
}
