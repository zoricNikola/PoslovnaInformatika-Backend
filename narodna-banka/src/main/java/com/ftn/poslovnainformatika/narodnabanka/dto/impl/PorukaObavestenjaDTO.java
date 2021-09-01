package com.ftn.poslovnainformatika.narodnabanka.dto.impl;

import com.ftn.poslovnainformatika.narodnabanka.dto.impl.poslovnabanka.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.porukaobavestenja.PorukaObavestenjaViewDTO;
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
public class PorukaObavestenjaDTO  implements PorukaObavestenjaViewDTO {
    private int id;
    private VrstaPoruke vrstaPoruke;
    private String swiftKod;
    private String obracunskiRacun;
    private LocalDate datumValute;
    private String sifraValute;
    private double iznos;
    private PorukaDTO poruka;
    private PoslovnaBankaDTO poslovnaBanka;
}
