package com.ftn.poslovnainformatika.narodnabanka.service.impl;

import com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji.IzvodObracunskogRacunaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.izvestaji.PorukaIzvodaDTO;
import com.ftn.poslovnainformatika.narodnabanka.service.PorukaService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IzvestajService implements com.ftn.poslovnainformatika.narodnabanka.service.IzvestajService {

    @Autowired
    private PorukaService porukaService;

    @Override
    public boolean exportIzvestaj(int sifraBanke, LocalDate startDatum, LocalDate endDatum) throws FileNotFoundException, JRException {
        JasperReport jasperReport;
        List<PorukaIzvodaDTO> poruke = porukaService.getPorukeIzvoda(sifraBanke, startDatum, endDatum);
        System.out.println("PORUKEE: " + poruke.size());
        File file = ResourceUtils.getFile("classpath:poruke_i_nalozi.jrxml");
        File fileSubreport = ResourceUtils.getFile("classpath:subreport_nalozi.jrxml");
        jasperReport = JasperCompileManager.compileReport(fileSubreport.getAbsolutePath());
        jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(poruke);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Narodna banka Srbije");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "src/main/resources" + "/report_" + sifraBanke + ".pdf");
        return true;
    }
}
