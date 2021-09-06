package com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji;

import com.ftn.poslovnainformatika.narodnabanka.dto.DnevnoStanjeDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.PoslovnaBankaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StanjeObracunskogRacunaDTO {

    private PoslovnaBankaDTO poslovnaBanka;

    private DnevnoStanjeDTO dnevnoStanje;
}
