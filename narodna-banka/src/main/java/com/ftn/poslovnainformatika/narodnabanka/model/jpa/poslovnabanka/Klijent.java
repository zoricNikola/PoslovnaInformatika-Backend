package com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "klijent")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Klijent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "klijent_id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "ime", nullable = false, length = 20)
    private String ime;

    @Column(name = "prezime", nullable = false, length = 20)
    private String prezime;

    @Column(name = "naziv", nullable = false, length = 40)
    private String naziv;

    @Column(name = "adresa", nullable = false, length = 40)
    private String adresa;

    @Column(name = "pib")
    private Integer pib;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "delatnost", referencedColumnName = "delatnost_id")
    private Delatnost delatnost;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "mesto", referencedColumnName = "mesto_id", nullable = false)
    private Mesto mesto;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "klijent")
    private Set<TekuciRacun> racuni;


}
