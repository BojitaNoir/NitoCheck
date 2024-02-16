package mx.edu.utez.alamacen.controller.person;

import lombok.AllArgsConstructor;
import mx.edu.utez.alamacen.config.ApiResponse;
import mx.edu.utez.alamacen.controller.person.dto.PersonDto;
import mx.edu.utez.alamacen.service.person.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/almacen/person")
@CrossOrigin(origins  ={"*"})
@AllArgsConstructor

public class PersonController {

    private final PersonService personService;

    @PostMapping("/")
    public ResponseEntity <ApiResponse> register(@RequestBody PersonDto personDto){
        return personService.save(personDto.toEntity());

    }

    @PutMapping("/{id}")
    public ResponseEntity <ApiResponse> update(@PathVariable long id, @RequestBody PersonDto personDto){
        return personService.update(id, personDto.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <ApiResponse> delete(@PathVariable long id){
        return personService.delete(id);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return personService.getAll();
    }

    @GetMapping("/curp/{curp}")
    public ResponseEntity<ApiResponse> getByCurp(@PathVariable String curp){
        return personService.getByCurp(curp);
    }





}
