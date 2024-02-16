package mx.edu.utez.alamacen.service.client;

import lombok.AllArgsConstructor;
import mx.edu.utez.alamacen.config.ApiResponse;
import mx.edu.utez.alamacen.model.client.ClientBean;
import mx.edu.utez.alamacen.model.client.ClientRepository;
import mx.edu.utez.alamacen.model.person.PersonBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> save(ClientBean client){
        Optional<ClientBean> founClient = clientRepository.findByEmail(client.getEmail());
        if (founClient.isPresent())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                    "El registro ya existe"),HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(new ApiResponse(clientRepository.saveAndFlush(client), HttpStatus.OK,
                    false, "Registro exitoso"),HttpStatus.OK);
    }
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(long id){
        Optional<ClientBean> foundClient = clientRepository.findById(id);
        if (foundClient.isPresent()) {
            clientRepository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, true,
                    "Registro Borrado"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "El registro no existe"), HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> update(long id, ClientBean entity) {
        Optional<ClientBean> foundClient = clientRepository.findById(id);
        if(foundClient.isPresent()){
            entity.setId(id);
            return new ResponseEntity<>(new ApiResponse( clientRepository.saveAndFlush(entity), HttpStatus.OK, false, "Actualizacion exitoso"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse( HttpStatus.BAD_REQUEST, true, "El registro no existe"), HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(clientRepository.findAll(),
                HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> getByEmail(String email) {
        Optional<ClientBean> foundClient = clientRepository.findByEmail(email);
        if(foundClient.isPresent()){
            return new ResponseEntity<>(new ApiResponse(foundClient.get(), HttpStatus.OK), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse( HttpStatus.BAD_REQUEST, true, "El registro no existe"), HttpStatus.BAD_REQUEST);
    }

}
