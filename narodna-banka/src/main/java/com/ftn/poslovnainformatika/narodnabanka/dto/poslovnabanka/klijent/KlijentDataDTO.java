package com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.klijent;

import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.mesto.MestoViewDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.porukaobavestenja.PorukaObavestenjaViewDTO;

public interface KlijentDataDTO {

    String getIme();
    void setIme(String ime);

    String getPrezime();
    void setPrezime(String prezime);

    String getNaziv();
    void setNaziv(String naziv);

    String getAdresa();
    void setAdresa(String adresa);

    int getPib();
    void setPib(int pib);

    MestoViewDTO getMesto();
    void setMesto(MestoViewDTO mesto);

    PorukaObavestenjaViewDTO getSifarnikDelatnosti();
    void setSifarnikDelatnosti(PorukaObavestenjaViewDTO sifarnikDelatnosti);

}
