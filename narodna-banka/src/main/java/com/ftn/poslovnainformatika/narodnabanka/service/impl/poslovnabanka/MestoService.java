package com.ftn.poslovnainformatika.narodnabanka.service.impl.poslovnabanka;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.MestoDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Mesto;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.MestoRepository;

@Service
public class MestoService implements com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka.MestoService {

	@Autowired
	private MestoRepository mestoRepo;
	
	@Autowired
	private DtoConverter<Mesto, MestoDTO> mestoConverter;
	
	@Override
	public MestoDTO getOne(int id) {
		Mesto m = mestoRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		return mestoConverter.convertToDTO(m);
	}

	@Override
	public int create(MestoDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(int id, MestoDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<MestoDTO> getAll() {
		Set<Mesto> m = new HashSet<>(mestoRepo.findAll());
		
		return mestoConverter.convertToDTO(m);
	}

}
