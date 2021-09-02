package com.ftn.poslovnainformatika.narodnabanka.repository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.PorukaObavestenja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PorukaObavestenjaRepository extends JpaRepository<PorukaObavestenja, Integer> {
    Page<PorukaObavestenja> findByPoruka_Id(int porukaId, Pageable pageable);
    Page<PorukaObavestenja> findByPoslovnaBanka_SifraBanke(int sifraPoslovneBanke, Pageable pageable);
}
