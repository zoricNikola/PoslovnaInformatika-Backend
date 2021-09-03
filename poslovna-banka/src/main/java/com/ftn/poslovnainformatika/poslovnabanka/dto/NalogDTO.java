package com.ftn.poslovnainformatika.poslovnabanka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NalogDTO {

    private Integer id;

    private String duznik;

    private String poverilac;

    private String racunDuznika;

    private String racunPoverioca;

    private String svrhaPlacanja;

    private Double iznos;

    private String sifraValute;

    private LocalDate datum;

    private boolean hitno;

    private String pozivNaBrojZaduzenja;

    private String pozivNaBrojOdobrenja;

    private Integer modelZaduzenja;

    private Integer modelOdobrenja;
}
