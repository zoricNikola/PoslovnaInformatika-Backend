package com.ftn.poslovnainformatika.narodnabanka.service;

import com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji.IzvodObracunskogRacunaDTO;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.time.LocalDate;

public interface IzvestajService {

    boolean exportIzvestaj(int sifraBanke, LocalDate startDatum, LocalDate endDatum) throws FileNotFoundException, JRException;
}
