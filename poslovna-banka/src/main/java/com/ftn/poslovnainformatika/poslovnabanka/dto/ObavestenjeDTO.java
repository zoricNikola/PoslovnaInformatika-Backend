package com.ftn.poslovnainformatika.poslovnabanka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ObavestenjeDTO {

    private Integer id;

    private VrstaObavestenja vrstaObavestenja;

    private String swiftKod;

    private String obracunskiRacun;

    private LocalDate datumValute;

    private String sifraValute;

    private Double iznos;

    private PorukaDTO poruka;

    private PoslovnaBankaDTO poslovnaBanka;

    @Override
    public String toString() {
        return "Obavestenje {" +
                ", vrstaObavestenja = " + vrstaObavestenja +
                ", swiftKod = '" + swiftKod + '\'' +
                ", obracunskiRacun = '" + obracunskiRacun + '\'' +
                ", datumValute = " + datumValute +
                ", sifraValute = '" + sifraValute + '\'' +
                ", iznos = " + iznos +
                ", poruka = " + poruka +
                ", poslovnaBanka = " + poslovnaBanka +
                '}';
    }
}
