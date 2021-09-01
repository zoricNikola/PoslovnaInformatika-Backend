package com.ftn.poslovnainformatika.narodnabanka.repository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.SifarnikDelatnosti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SifarnikDelatnostiRepository extends JpaRepository<SifarnikDelatnosti, Integer> {
}
