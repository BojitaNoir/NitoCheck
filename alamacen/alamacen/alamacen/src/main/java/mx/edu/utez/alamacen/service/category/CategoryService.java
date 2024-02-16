package mx.edu.utez.alamacen.service.category;

import lombok.AllArgsConstructor;
import mx.edu.utez.alamacen.config.ApiResponse;
import mx.edu.utez.alamacen.model.category.CategoryBean;
import mx.edu.utez.alamacen.model.category.CategoryRepository;
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
public class CategoryService {

    public final CategoryRepository categoryRepository;
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> save(CategoryBean category){
        Optional<CategoryBean> foundCategory = categoryRepository.findByCodigo(category.getCodigo());
        if (foundCategory.isPresent())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                    "El registro ya existe"),HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new ApiResponse(categoryRepository.saveAndFlush(category),HttpStatus.OK, false
                , "Registro exitoso"),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(long id){
        Optional<CategoryBean> foundCategory = categoryRepository.findById(id);
        if (foundCategory.isPresent()) {
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, true,
                    "Registro Borrado"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "El registro no existe"), HttpStatus.BAD_REQUEST);
    }
    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> update(long id, CategoryBean entity) {
        Optional<CategoryBean> foundCategory = categoryRepository.findById(id);
        if(foundCategory.isPresent()){
            entity.setId(id);
            return new ResponseEntity<>(new ApiResponse( categoryRepository.saveAndFlush(entity), HttpStatus.OK, false, "Actualizacion exitoso"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse( HttpStatus.BAD_REQUEST, true, "El registro no existe"), HttpStatus.BAD_REQUEST);

    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<>(new ApiResponse(categoryRepository.findAll(), HttpStatus.OK, false,"Se realizo la bsqueda de manera exitosa"), HttpStatus.OK);
    }

    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<ApiResponse> getByCodigo(String codigo) {
        Optional<CategoryBean> foundCategory = categoryRepository.findByCodigo(codigo);
        if(foundCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(foundCategory.get(), HttpStatus.OK), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse( HttpStatus.BAD_REQUEST, true, "El registro no existe"), HttpStatus.BAD_REQUEST);
    }
}
