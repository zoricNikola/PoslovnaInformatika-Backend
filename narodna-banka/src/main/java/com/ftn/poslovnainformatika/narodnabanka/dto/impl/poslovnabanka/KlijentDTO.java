package com.ftn.poslovnainformatika.narodnabanka.dto.impl.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.klijent.KlijentViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.mesto.MestoViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.porukaobavestenja.PorukaObavestenjaViewDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KlijentDTO implements KlijentViewDTO {

    private int id;

    private String ime;

    private String prezime;

    private String naziv;

    private String adresa;

    private int pib;

    private MestoViewDTO mesto;

    private PorukaObavestenjaViewDTO sifarnikDelatnosti;

}
