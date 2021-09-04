package com.ftn.poslovnainformatika.poslovnabanka.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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
}
