package com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IzvodObracunskogRacunaDTO {

    private Set<PorukaIzvodaDTO> porukeIzvoda;
}
