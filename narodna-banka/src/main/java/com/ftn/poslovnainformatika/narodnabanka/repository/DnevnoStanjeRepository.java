package com.ftn.poslovnainformatika.narodnabanka.repository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.DnevnoStanje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DnevnoStanjeRepository extends JpaRepository<DnevnoStanje, Integer> {

    Page<DnevnoStanje> findByObracunskiRacun_BrojObracunskogRacuna(String brojObracunskogRacuna, Pageable pageable);
}
