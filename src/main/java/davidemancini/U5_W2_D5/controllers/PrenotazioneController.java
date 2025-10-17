package davidemancini.U5_W2_D5.controllers;

import davidemancini.U5_W2_D5.entities.Dipendente;
import davidemancini.U5_W2_D5.entities.Prenotazione;
import davidemancini.U5_W2_D5.exceptions.MyValidationException;
import davidemancini.U5_W2_D5.payloads.NewDipendenteDTO;
import davidemancini.U5_W2_D5.payloads.NewPrenotazioneDTO;
import davidemancini.U5_W2_D5.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;


    //SALVA UNA PRENOTAZIONE (STATUS CODE 201)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione savePrenotazione(@RequestBody @Validated NewPrenotazioneDTO body, BindingResult bindingResult){
        //bindinresult MI PERMETTE DI GESTIRE GLI ERRORI PIU FACILMENTE
        if (bindingResult.hasErrors()){
            throw new MyValidationException(bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return prenotazioneService.save(body);

    }

    //TUTTE LE PRENOTAZIONI
    @GetMapping
    public Page<Prenotazione> getPrenotazioni(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "note") String sortBy){
        return prenotazioneService.findAll(page,size,sortBy);
    }


    //RITORNA PRENOTAZIONE SPECIFICA TRAMITE ID
    @GetMapping("/{reservationId}")
    public Prenotazione getPrenotazioneById(@PathVariable UUID reservationId){
        return prenotazioneService.findById(reservationId);
    }
    //ELIMINA UNA PRENOTAZIONE
    @DeleteMapping("/{reservationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente (@PathVariable UUID reservationId){
        prenotazioneService.findByIdAndDelete(reservationId);
    }
    //AGGIORNA PRENOTAZIONE
    @PutMapping("/{reservationId}")
    public Prenotazione findByIdAndUpdate(@PathVariable UUID reservationId, @RequestBody @Validated NewPrenotazioneDTO body,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new MyValidationException(bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return prenotazioneService.findByIdAndUpdate(reservationId,body);
    }
}
