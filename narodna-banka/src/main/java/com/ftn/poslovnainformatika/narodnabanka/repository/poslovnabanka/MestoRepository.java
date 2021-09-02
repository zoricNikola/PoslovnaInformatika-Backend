package com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Mesto;

public interface MestoRepository extends JpaRepository<Mesto, Integer> {

}
