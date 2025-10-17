package davidemancini.U5_W2_D5.services;

import davidemancini.U5_W2_D5.entities.Dipendente;
import davidemancini.U5_W2_D5.exceptions.NotFoundException;
import davidemancini.U5_W2_D5.payloads.NewDipendenteDTO;
import davidemancini.U5_W2_D5.repositories.DipendenteRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;


    public Dipendente save(NewDipendenteDTO body){
        Dipendente newDipendente = new Dipendente(body.username(),body.nome(), body.cognome(), body.email());
        return dipendenteRepository.save(newDipendente);
    }
    public Page<Dipendente> findAll(int pageNumber, int pageSize,String pageSortBy){
        if(pageSize>30) pageSize=30;
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(pageSortBy));
        return dipendenteRepository.findAll(pageable);
    }
    public Dipendente findById (UUID id){
        return dipendenteRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    public Dipendente findByIdAndUpdate(UUID id, NewDipendenteDTO body){
        Dipendente trovato = findById(id);
        if (!trovato.getEmail().equals(body.email())){

            dipendenteRepository.findByEmail(body.email());
            };
        trovato.setNome(body.nome());
        trovato.setCognome(body.cognome());
        trovato.setUsername(body.username());
        trovato.setEmail(body.email());
        Dipendente dipendenteModificato = dipendenteRepository.save(trovato);
        return dipendenteModificato;
        }

    public void findByIdAndDelete(UUID id){
        Dipendente trovato = findById(id);
        dipendenteRepository.delete(trovato);
    }
    }

