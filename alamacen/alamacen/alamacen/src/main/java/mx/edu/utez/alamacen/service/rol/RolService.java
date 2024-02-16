package mx.edu.utez.alamacen.service.rol;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mx.edu.utez.alamacen.config.ApiResponse;
import mx.edu.utez.alamacen.model.rol.RolBean;
import mx.edu.utez.alamacen.model.rol.RolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@jakarta.transaction.Transactional
@AllArgsConstructor
public class RolService {


    private final RolRepository rolRepository;

    @jakarta.transaction.Transactional(rollbackOn = Exception.class)
    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<>(new ApiResponse(rolRepository.findAll(), HttpStatus.OK), HttpStatus.OK);

    }


    @jakarta.transaction.Transactional(rollbackOn = Exception.class)
    public ResponseEntity<ApiResponse> save(RolBean role) {
        Optional<RolBean> foundRol = rolRepository.findByRol(role.getRol());
        if (foundRol.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "El registro ya existe"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse(rolRepository.saveAndFlush(role), HttpStatus.OK, false, "Resgistro exitoso"), HttpStatus.OK);

    }

    @jakarta.transaction.Transactional(rollbackOn = Exception.class)
    public ResponseEntity<ApiResponse> update(long id, RolBean role) {
        Optional<RolBean> foundRol = rolRepository.findById(id);
        if (foundRol.isPresent()) {
            role.setId(id);
            return new ResponseEntity<>(new ApiResponse(rolRepository.saveAndFlush(role), HttpStatus.OK, false, "Actualizacion exitoso"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "El registro no existe"), HttpStatus.BAD_REQUEST);
    }


    @jakarta.transaction.Transactional(rollbackOn = Exception.class)
    public ResponseEntity<ApiResponse> delete(long id) {
        Optional<RolBean> foundRol = rolRepository.findById(id);
        if (foundRol.isPresent()) {
            rolRepository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Eliminacion exitoso"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "El registro no existe"), HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<ApiResponse> getById(long id) {
        Optional<RolBean> foundRol = rolRepository.findById(id);
        if (foundRol.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(foundRol.get(), HttpStatus.OK), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "El registro no existe"), HttpStatus.BAD_REQUEST);
    }



}