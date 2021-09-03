package com.ftn.poslovnainformatika.narodnabanka.service.impl.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.PoslovnaBanka;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.PoslovnaBankaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PoslovnaBankaService implements com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka.PoslovnaBankaService {

    @Autowired
    private DtoConverter<PoslovnaBanka, PoslovnaBankaDTO> poslovnaBankaConverter;

    @Autowired
    private PoslovnaBankaRepository poslovnaBankaRepo;

    @Override
    public PoslovnaBankaDTO getOne(int id) {
        PoslovnaBanka poslovnaBanka = poslovnaBankaRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return poslovnaBankaConverter.convertToDTO(poslovnaBanka);
    }

    @Override
    public int create(PoslovnaBankaDTO dto) {
        if(poslovnaBankaRepo.findBySwiftKod(dto.getSwiftKod()).isPresent()) throw new IllegalArgumentException();
        if(poslovnaBankaRepo.findByObracunskiRacun_BrojObracunskogRacuna(
        		dto.getObracunskiRacun().getBrojObracunskogRacuna()).isPresent()) throw new IllegalArgumentException();

        PoslovnaBanka poslovnaBanka = poslovnaBankaConverter.convertToJPA(dto);

        poslovnaBanka = poslovnaBankaRepo.save(poslovnaBanka);

        return poslovnaBanka.getSifraBanke();
    }

    @Override
    public void update(int id, PoslovnaBankaDTO dto) {
        PoslovnaBanka poslovnaBanka = poslovnaBankaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException());

        if(poslovnaBanka.getSwiftKod() != dto.getSwiftKod()) throw new IllegalArgumentException();
        if(!poslovnaBanka.getObracunskiRacun().getBrojObracunskogRacuna()
        		.equals(dto.getObracunskiRacun().getBrojObracunskogRacuna())) throw new IllegalArgumentException();

        poslovnaBanka.setNazivBanke(dto.getNazivBanke());

        poslovnaBankaRepo.save(poslovnaBanka);
    }

    @Override
    public void delete(int id) {
        PoslovnaBanka poslovnaBanka = poslovnaBankaRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());

        poslovnaBankaRepo.deleteById(id);
    }
}
