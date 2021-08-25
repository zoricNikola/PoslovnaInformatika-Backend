package com.ftn.poslovnainformatika.narodnabanka.model.jpa;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Klijent;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.PoslovnaBanka;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "racun")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Racun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "racun_id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "klijent", referencedColumnName = "klijent_id", nullable = false)
    private Klijent klijent;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "poslovna_banka", referencedColumnName = "sifra_banke", nullable = false)
    private PoslovnaBanka poslovnaBanka;
}
