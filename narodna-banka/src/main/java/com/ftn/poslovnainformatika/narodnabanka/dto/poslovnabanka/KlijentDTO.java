package com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KlijentDTO {

    private Integer id;

    private String ime;

    private String prezime;

    private String naziv;

    private String adresa;

    private Integer pib;

    private MestoDTO mesto;
    
    private DelatnostDTO delatnost;

}
