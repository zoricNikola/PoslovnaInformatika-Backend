package com.ftn.poslovnainformatika.poslovnabanka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IzvodObracunskogRacunaDTO {

    private Set<PorukaIzvodaDTO> porukeIzvoda;

    @Override
    public String toString() {
        return "IzvodObracunskogRacunaDTO {" +
                "porukeIzvoda = " + porukeIzvoda +
                '}';
    }
}
