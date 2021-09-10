package com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TekuciRacunDTO {
    private String brojRacuna;
    private PoslovnaBankaDTO poslovnaBanka;
    private KlijentDTO klijent;
}
