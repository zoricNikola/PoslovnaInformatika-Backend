package com.ftn.poslovnainformatika.narodnabanka.model.jpa;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.PoslovnaBanka;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "poruka_obavestenja")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PorukaObavestenja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poruka_obavestenja_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "vrsta_poruke")
    private VrstaPoruke vrstaPoruke;

    @Column(name = "swift_kod")
    private String swiftKod;

    @Column(name = "obracunski_racun")
    private String obracunskiRacun;

    @Column(name = "datum_valute")
    private LocalDate datumValute;

    @Column(name = "sifra_valute")
    private String sifraValute;

    @Column(name = "iznos", precision = 15, scale = 2, nullable = false)
    private double iznos;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "poruka", referencedColumnName = "poruka_id", nullable = false)
    private Poruka poruka;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "poslovna_banka", referencedColumnName = "sifra_banke", nullable = false)
    private PoslovnaBanka poslovnaBanka;
}
