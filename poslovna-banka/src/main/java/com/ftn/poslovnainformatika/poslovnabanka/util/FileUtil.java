package com.ftn.poslovnainformatika.poslovnabanka.util;

import com.ftn.poslovnainformatika.poslovnabanka.dto.NalogDTO;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class FileUtil {

    public Set<NalogDTO> getNalozi(String path) {
        Set<NalogDTO> nalozi = new HashSet<>();
        try{
            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String st;
            NalogDTO nalogDTO = null;

            while((st = reader.readLine()) != null){
                String[] splited = st.split("\\|");
                String duznik = splited[0];
                String racunDuznika = splited[1];
                String poverilac = splited[2];
                String racunPoverioca = splited[3];
                String svrhaPLacanja = splited[4];
                Double iznos = Double.parseDouble(splited[5]);
                String sifraValute = splited[6];
                LocalDate datum = LocalDate.parse(splited[7]);
                boolean hitno = Boolean.parseBoolean(splited[8]);
                String pozivNaBrojZaduzenja = splited[9];
                String pozivNaBrojOdobrenja = splited[10];
                Integer modelZaduzenja = Integer.parseInt(splited[11]);
                Integer modelOdobrenja = Integer.parseInt(splited[12]);

                nalogDTO = new NalogDTO(null, duznik, poverilac, racunDuznika, racunPoverioca, svrhaPLacanja,
                        iznos, sifraValute, datum, hitno, pozivNaBrojZaduzenja, pozivNaBrojOdobrenja, modelZaduzenja, modelOdobrenja);

                nalozi.add(nalogDTO);
            }
            reader.close();

        }catch(IOException ex){
            ex.printStackTrace();
        }
        return nalozi;
    }
}
