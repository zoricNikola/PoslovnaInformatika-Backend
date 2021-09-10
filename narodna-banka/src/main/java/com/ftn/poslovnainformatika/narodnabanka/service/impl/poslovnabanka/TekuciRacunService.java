package com.ftn.poslovnainformatika.narodnabanka.service.impl.poslovnabanka;

import com.ftn.poslovnainformatika.narodnabanka.converter.DtoConverter;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.KlijentDTO;
import com.ftn.poslovnainformatika.narodnabanka.dto.poslovnabanka.TekuciRacunDTO;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.Klijent;
import com.ftn.poslovnainformatika.narodnabanka.model.jpa.poslovnabanka.TekuciRacun;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.KlijentRepository;
import com.ftn.poslovnainformatika.narodnabanka.repository.poslovnabanka.TekuciRacunRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TekuciRacunService implements com.ftn.poslovnainformatika.narodnabanka.service.poslovnabanka.TekuciRacunService{
    @Autowired
    private DtoConverter<TekuciRacun, TekuciRacunDTO> tekuciRacunConverter;

    @Autowired
    private TekuciRacunRepository tekuciRacunRepository;

    @Autowired
    private KlijentService klijentService;

    @Autowired
    private DtoConverter<Klijent, KlijentDTO> klijentConverter;

    @Autowired
    KlijentRepository klijentRepository;

    @Override
    public TekuciRacunDTO getOne(String id) {
        TekuciRacun tekuciRacun = tekuciRacunRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return tekuciRacunConverter.convertToDTO(tekuciRacun);
    }

    @Override
    public int create(TekuciRacunDTO tekuciRacunDTO) {
        return 0;
    }

	@Override
    public String createe(TekuciRacunDTO tekuciRacunDTO) {
        TekuciRacun tekuciRacun;

        if (tekuciRacunDTO.getKlijent().getId() == null) {
            KlijentDTO klijentDTO = new KlijentDTO(null, tekuciRacunDTO.getKlijent().getIme(), tekuciRacunDTO.getKlijent().getPrezime(),
                    tekuciRacunDTO.getKlijent().getNaziv(), tekuciRacunDTO.getKlijent().getAdresa(),
                    tekuciRacunDTO.getKlijent().getPib(), tekuciRacunDTO.getKlijent().getMesto(),
                    tekuciRacunDTO.getKlijent().getDelatnost());

            Klijent klijent = klijentRepository.save(klijentConverter.convertToJPA(klijentDTO));

            tekuciRacun = tekuciRacunConverter.convertToJPA(tekuciRacunDTO);
            tekuciRacun.setBrojRacuna(tekuciRacunDTO.getBrojRacuna());
            tekuciRacun.setKlijent(klijent);
            tekuciRacun = tekuciRacunRepository.save(tekuciRacun);
        }else {
            tekuciRacun = tekuciRacunConverter.convertToJPA(tekuciRacunDTO);
            tekuciRacun = tekuciRacunRepository.save(tekuciRacun);
        }

        return tekuciRacun.getBrojRacuna();
    }

    @Override
    public void delete(String id) {
        TekuciRacun tekuciRacun = tekuciRacunRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        tekuciRacunRepository.deleteById(id);
    }

    public Set<TekuciRacunDTO> getAll(){
        List<TekuciRacun> tekuciRacuni = tekuciRacunRepository.findAll();

        Set<TekuciRacun> tekuciRacuniSet = new HashSet<>();
        for (TekuciRacun tr : tekuciRacuni) tekuciRacuniSet.add(tr);

        return tekuciRacunConverter.convertToDTO(tekuciRacuniSet);
    }

	@Override
	public TekuciRacunDTO getOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(int id, TekuciRacunDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<TekuciRacunDTO> getRacuniByBanka(int sifraBanke) {
		Set<TekuciRacun> racuni = tekuciRacunRepository.findByPoslovnaBanka_SifraBanke(sifraBanke);
		return tekuciRacunConverter.convertToDTO(racuni);
	}
}
