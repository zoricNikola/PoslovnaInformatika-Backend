package com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.ObracunskiRacun;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "poslovna_banka")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PoslovnaBanka {

    @Id
    @Column(name = "sifra_banke", unique = true, nullable = false)
    private Integer sifraBanke;

    @Column(name = "naziv", nullable = false, length = 30)
    private String nazivBanke;

    @Column(name = "swift_kod", nullable = false, length = 8)
    private String swiftKod;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "obracunski_racun", referencedColumnName = "broj_obracunskog_racuna", nullable = false)
    private ObracunskiRacun obracunskiRacun;

}
