package davidemancini.U5_W2_D5.controllers;

import davidemancini.U5_W2_D5.entities.Dipendente;
import davidemancini.U5_W2_D5.exceptions.MyValidationException;
import davidemancini.U5_W2_D5.payloads.NewDipendenteDTO;
import davidemancini.U5_W2_D5.services.DipendenteService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


//GET http://localhost:3001/users
//POST http://localhost:3001/users (+ request body)
//GET http://localhost:3001/users/{userId}
//PUT http://localhost:3001/users/{userId} (+ request body)
//DELETE  http://localhost:3001/users/{userId}

@RestController
@RequestMapping("/employees")
public class DipendenteController {
@Autowired
private DipendenteService dipendenteService;

    //SALVA UN DIPENDENTE (STATUS CODE 201)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody @Validated NewDipendenteDTO body, BindingResult bindingResult){
        //bindinresult MI PERMETTE DI GESTIRE GLI ERRORI PIU FACILMENTE
        if (bindingResult.hasErrors()){
            throw new MyValidationException(bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return dipendenteService.save(body);

    }
    //TUTTI I DIPENDENTI
    @GetMapping
    public Page<Dipendente> getDipendenti(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "nome") String sortBy){
        return dipendenteService.findAll(page,size,sortBy);
    }
    //RITORNA DIPENDENTE SPECIFICO TRAMITE ID
    @GetMapping("/{employeeId}")
    public Dipendente getDipendenteById(@PathVariable UUID employeeId){
        return dipendenteService.findById(employeeId);
    }
    //ELIMINA UN DIPENDENTE
    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente (@PathVariable UUID employeeId){
        dipendenteService.findByIdAndDelete(employeeId);
    }
    //AGGIORNA DIPENDENTE
    @PutMapping("/{employeeId}")
    public Dipendente findByIdAndUpdate(@PathVariable UUID employeeId, @RequestBody @Validated NewDipendenteDTO body,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new MyValidationException(bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
       return dipendenteService.findByIdAndUpdate(employeeId,body);
    }

}
