package mx.edu.utez.alamacen.controller.rol;

import mx.edu.utez.alamacen.config.ApiResponse;
import mx.edu.utez.alamacen.controller.rol.dto.RolDto;
import mx.edu.utez.alamacen.service.rol.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/role")
@CrossOrigin(origins = {"*"})
public class RolController {

    private  final RolService service;

    public RolController(RolService service){
        this.service = service;
    }


    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable long id){
        return service.getById(id);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> save(@RequestBody RolDto roleDto){
        return service.save(roleDto.toEntity());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable long id, @RequestBody RolDto roleDto){
        return service.update(id, roleDto.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable long id){
        return service.delete(id);
    }





}