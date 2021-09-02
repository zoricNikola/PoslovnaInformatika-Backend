package com.ftn.poslovnainformatika.narodnabanka.model.jpa;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.PoslovnaBanka;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "obracunski_racun")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObracunskiRacun {

    @Id
    @Column(name = "broj_obracunskog_racuna", unique = true, nullable = false, length = 18)
    private String brojObracunskogRacuna;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "obracunskiRacun")
    private PoslovnaBanka poslovnaBanka;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "obracunskiRacun")
    private Set<DnevnoStanje> dnevnaStanja;
}
