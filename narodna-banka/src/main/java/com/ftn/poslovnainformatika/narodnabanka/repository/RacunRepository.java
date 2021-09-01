package com.ftn.poslovnainformatika.narodnabanka.repository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.Racun;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RacunRepository extends JpaRepository<Racun, Integer> {
    Page<Racun> findByPoslovnaBanka_Id(int poslovnaBankaId, Pageable pageable);
    Page<Racun> findByKlijent_Id(int klijentId, Pageable pageable);
}
