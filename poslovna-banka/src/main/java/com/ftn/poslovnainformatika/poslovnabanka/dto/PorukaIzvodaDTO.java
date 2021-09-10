package com.ftn.poslovnainformatika.poslovnabanka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PorukaIzvodaDTO {

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
        return "PorukaIzvodaDTO{" +
                "id = " + id +
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
