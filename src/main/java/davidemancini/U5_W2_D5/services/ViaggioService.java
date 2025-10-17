package davidemancini.U5_W2_D5.services;

import davidemancini.U5_W2_D5.entities.Viaggio;
import davidemancini.U5_W2_D5.exceptions.NotFoundException;
import davidemancini.U5_W2_D5.payloads.NewViaggioDTO;
import davidemancini.U5_W2_D5.repositories.ViaggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ViaggioService {
    @Autowired
    private ViaggiRepository viaggiRepository;


    public Viaggio save(NewViaggioDTO body){
        Viaggio newViaggio = new Viaggio(body.destinazione(),body.data_viaggio(),body.stato());
        return viaggiRepository.save(newViaggio);
    }
    public Viaggio findById(UUID id){
        return viaggiRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    public Viaggio findByIdAndDelete(UUID id, NewViaggioDTO body){
        Viaggio trovato = findById(id);
        trovato.setData_viaggio(body.data_viaggio());
        trovato.setDestinazione(body.destinazione());
        Viaggio viaggioModificato = viaggiRepository.save(trovato);
        return viaggioModificato;
    }
    public void findByIdAndDelete(UUID id){
        Viaggio trovato = findById(id);
        viaggiRepository.delete(trovato);
    }
}
