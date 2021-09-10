package com.ftn.poslovnainformatika.narodnabanka.service.impl.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.PoslovnaBankaDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.TekuciRacunDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.PoslovnaBanka;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.PoslovnaBankaRepository;
import com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka.KlijentService;
import com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka.TekuciRacunService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PoslovnaBankaService implements com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka.PoslovnaBankaService {

    @Autowired
    private DtoConverter<PoslovnaBanka, PoslovnaBankaDTO> poslovnaBankaConverter;

    @Autowired
    private PoslovnaBankaRepository poslovnaBankaRepo;
    
    @Autowired
    private KlijentService klijentService;
    
    @Autowired
    private TekuciRacunService racunService;

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

    @Override
    public Set<PoslovnaBankaDTO> getAll() {
        List<PoslovnaBanka> poslovneBanke = poslovnaBankaRepo.findAll();

        return poslovnaBankaConverter.convertToDTO(new HashSet<>(poslovneBanke));
    }

	@Override
	public Set<KlijentDTO> getPoslovnaBankaKlijenti(int sifraBanke) {
		return klijentService.getKlijentiByBanka(sifraBanke);
	}
	
	@Override
	public Set<TekuciRacunDTO> getPoslovnaBankaRacuni(int sifraBanke) {
		return racunService.getRacuniByBanka(sifraBanke);
	}
}
