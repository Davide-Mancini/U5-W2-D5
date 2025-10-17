package davidemancini.U5_W2_D5.services;

import davidemancini.U5_W2_D5.entities.Dipendente;
import davidemancini.U5_W2_D5.entities.Prenotazione;
import davidemancini.U5_W2_D5.entities.Viaggio;
import davidemancini.U5_W2_D5.exceptions.NotFoundException;
import davidemancini.U5_W2_D5.payloads.NewPrenotazioneDTO;
import davidemancini.U5_W2_D5.repositories.DipendenteRepository;
import davidemancini.U5_W2_D5.repositories.PrenotazioneRepository;
import davidemancini.U5_W2_D5.repositories.ViaggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private ViaggiRepository viaggiRepository;

    public Prenotazione save(NewPrenotazioneDTO body){
        Dipendente dipendenteTrovato = dipendenteRepository.findById(body.dipendente()).orElseThrow(()-> new NotFoundException(body.dipendente()));
        Viaggio viaggioTrovato = viaggiRepository.findById(body.viaggio()).orElseThrow(()-> new NotFoundException(body.viaggio()));
        Prenotazione newPrenotazione = new Prenotazione(body.data_richiesta(), body.note(), dipendenteTrovato,viaggioTrovato);
        return prenotazioneRepository.save(newPrenotazione);
    }
    public Page<Prenotazione> findAll(int pageNumber, int pageSize, String pageSortBy){
        if(pageSize>30) pageSize=30;
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(pageSortBy));
        return prenotazioneRepository.findAll(pageable);
    }
    public Prenotazione findById (UUID id){
        return prenotazioneRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    public Prenotazione findByIdAndUpdate(UUID id, NewPrenotazioneDTO body){
        Dipendente dipendenteTrovato = dipendenteRepository.findById(body.dipendente()).orElseThrow(()-> new NotFoundException(body.dipendente()));
        Viaggio viaggioTrovato = viaggiRepository.findById(body.viaggio()).orElseThrow(()-> new NotFoundException(body.viaggio()));
        Prenotazione trovata = findById(id);
        trovata.setData_richiesta(body.data_richiesta());
        trovata.setNote(body.note());
        trovata.setDipendente(dipendenteTrovato);
        trovata.setViaggio(viaggioTrovato);
        Prenotazione prenotazioneModificata = prenotazioneRepository.save(trovata);
        return prenotazioneModificata;
    }
    public void findByIdAndDelete(UUID id){
        Prenotazione trovata = findById(id);
        prenotazioneRepository.delete(trovata);
    }
}
