package mx.edu.utez.alamacen.service.person;

import lombok.AllArgsConstructor;
import mx.edu.utez.alamacen.config.ApiResponse;
import mx.edu.utez.alamacen.model.person.PersonBean;
import mx.edu.utez.alamacen.model.person.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;


    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> save(PersonBean person){
        Optional<PersonBean> foundPerson = personRepository.findByCurp(person.getCurp());
        if(foundPerson.isPresent()){
            return new ResponseEntity<>(new ApiResponse( HttpStatus.BAD_REQUEST, true, "El registro ya existe"), HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(new ApiResponse( personRepository.saveAndFlush(person), HttpStatus.OK, false, "Resgistro exitoso"), HttpStatus.OK);
    }

    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> delete(long id) {
        Optional<PersonBean> foundPerson = personRepository.findById(id);
        if(foundPerson.isPresent()){
            personRepository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse( HttpStatus.OK, false, "El registro se ha eliminado"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse( HttpStatus.BAD_REQUEST, true, "El registro no existe"), HttpStatus.BAD_REQUEST);
    }
    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> update(long id, PersonBean entity) {
        Optional<PersonBean> foundPerson = personRepository.findById(id);
        if(foundPerson.isPresent()){
            entity.setId(id);
            return new ResponseEntity<>(new ApiResponse( personRepository.saveAndFlush(entity), HttpStatus.OK, false, "Actualizacion exitoso"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse( HttpStatus.BAD_REQUEST, true, "El registro no existe"), HttpStatus.BAD_REQUEST);

    }

    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<>(new ApiResponse(personRepository.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> getByCurp(String curp) {
        Optional<PersonBean> foundPerson = personRepository.findByCurp(curp);
        if(foundPerson.isPresent()){
            return new ResponseEntity<>(new ApiResponse(foundPerson.get(), HttpStatus.OK), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse( HttpStatus.BAD_REQUEST, true, "El registro no existe"), HttpStatus.BAD_REQUEST);
    }
}
