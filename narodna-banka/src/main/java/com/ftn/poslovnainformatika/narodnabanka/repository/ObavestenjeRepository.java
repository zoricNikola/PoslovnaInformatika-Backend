package com.ftn.poslovnainformatika.narodnabanka.repository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Obavestenje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObavestenjeRepository extends JpaRepository<Obavestenje, Integer> {
    Page<Obavestenje> findByPoruka_Id(int porukaId, Pageable pageable);
    Page<Obavestenje> findByPoslovnaBanka_SifraBanke(int sifraPoslovneBanke, Pageable pageable);
}
