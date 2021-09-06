package com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji;

import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.VrstaPoruke;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IzvodObracunskogRacunaDTO {

    private Integer id;

    private LocalDate datum;

    private VrstaPoruke vrstaPoruke;

    private Double ukupanIznos;

    private String sifraValute;

    private LocalDate datumValute;

    private PoslovnaBankaDTO bankaDuznika;

    private PoslovnaBankaDTO bankaPoverioca;
}
