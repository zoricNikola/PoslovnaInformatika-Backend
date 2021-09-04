package com.ftn.poslovnainformatika.poslovnabanka.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PorukaDTO {

    private Integer id;

    private LocalDate datum;

    private VrstaPoruke vrstaPoruke;

    private Double ukupanIznos;

    private String sifraValute;

    private LocalDate datumValute;

    private PoslovnaBankaDTO bankaDuznika;

    private PoslovnaBankaDTO bankaPoverioca;

    private Set<NalogDTO> nalozi;

    @Override
    public String toString() {
        return "Poruka {" +
                ", datum = " + datum +
                ", vrstaPoruke = " + vrstaPoruke +
                ", ukupanIznos = " + ukupanIznos +
                ", sifraValute = '" + sifraValute + '\'' +
                ", datumValute = " + datumValute +
                ", bankaDuznika = " + bankaDuznika +
                ", bankaPoverioca = " + bankaPoverioca +
                ", nalozi = " + nalozi +
                '}';
    }
}
