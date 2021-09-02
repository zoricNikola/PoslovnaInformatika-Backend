package com.ftn.poslovnainformatika.narodnabanka.dto;

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
public class ObavestenjeDTO {
    private Integer id;
    private VrstaPoruke vrstaPoruke;
    private String swiftKod;
    private String obracunskiRacun;
    private LocalDate datumValute;
    private String sifraValute;
    private Double iznos;
    private PorukaDTO poruka;
    private PoslovnaBankaDTO poslovnaBanka;
}
