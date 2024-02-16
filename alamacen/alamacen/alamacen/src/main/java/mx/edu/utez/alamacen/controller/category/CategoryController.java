package mx.edu.utez.alamacen.controller.category;

import lombok.AllArgsConstructor;
import mx.edu.utez.alamacen.config.ApiResponse;
import mx.edu.utez.alamacen.controller.category.dto.CategoryDto;
import mx.edu.utez.alamacen.controller.person.dto.PersonDto;
import mx.edu.utez.alamacen.service.category.CategoryService;
import mx.edu.utez.alamacen.service.client.ClientService;
import mx.edu.utez.alamacen.service.person.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/almacen/category")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor
public class CategoryController {
    private final CategoryService service;


    @PostMapping("/")
    public ResponseEntity<ApiResponse> register(@RequestBody CategoryDto dto){
        return service.save(dto.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <ApiResponse> delete(@PathVariable long id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity <ApiResponse> update(@PathVariable long id, @RequestBody CategoryDto categoryDto){
        return service.update(id, categoryDto.toEntity());
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<ApiResponse> getByCurp(@PathVariable String codigo){
        return service.getByCodigo(codigo);
    }


}
