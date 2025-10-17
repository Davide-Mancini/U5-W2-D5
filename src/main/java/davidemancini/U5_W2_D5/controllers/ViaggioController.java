package davidemancini.U5_W2_D5.controllers;

import davidemancini.U5_W2_D5.entities.Dipendente;
import davidemancini.U5_W2_D5.entities.Viaggio;
import davidemancini.U5_W2_D5.exceptions.MyValidationException;
import davidemancini.U5_W2_D5.payloads.NewDipendenteDTO;
import davidemancini.U5_W2_D5.payloads.NewViaggioDTO;
import davidemancini.U5_W2_D5.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/travels")
public class ViaggioController {
    @Autowired
    private ViaggioService viaggioService;


    //SALVA UN VIAGGIO (STATUS CODE 201)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio saveViaggio(@RequestBody @Validated NewViaggioDTO body, BindingResult bindingResult){
        //bindinresult MI PERMETTE DI GESTIRE GLI ERRORI PIU FACILMENTE
        if (bindingResult.hasErrors()){
            throw new MyValidationException(bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return viaggioService.save(body);

    }
    //TUTTI I VIAGGI
    @GetMapping
    public Page<Viaggio> getViaggi(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "destinazione") String sortBy){
        return viaggioService.findAll(page,size,sortBy);
    }
    //RITORNA VIAGGIO SPECIFICO TRAMITE ID
    @GetMapping("/{travelsId}")
    public Viaggio getViaggioById(@PathVariable UUID travelsId){
        return viaggioService.findById(travelsId);
    }
    //ELIMINA UN VIAGGIO
    @DeleteMapping("/{travelsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio (@PathVariable UUID travelsId){
        viaggioService.findByIdAndDelete(travelsId);
    }
    //AGGIORNA VIAGGIO
    @PutMapping("/{travelsId}")
    public Viaggio findByIdAndUpdate(@PathVariable UUID travelsId, @RequestBody @Validated NewViaggioDTO body,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new MyValidationException(bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return viaggioService.findByIdAndUpdate(travelsId,body);
    }
}
