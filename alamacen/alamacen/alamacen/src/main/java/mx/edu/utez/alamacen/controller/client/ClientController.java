package mx.edu.utez.alamacen.controller.client;

import lombok.AllArgsConstructor;
import mx.edu.utez.alamacen.config.ApiResponse;
import mx.edu.utez.alamacen.controller.client.dto.ClientDto;
import mx.edu.utez.alamacen.controller.person.dto.PersonDto;
import mx.edu.utez.alamacen.service.client.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/almacen/client")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor

public class ClientController {
    private final ClientService clientService;

    @PostMapping("/")
    public ResponseEntity <ApiResponse> register(@RequestBody ClientDto dto) {
    return clientService.save(dto.toEntity());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity <ApiResponse> delete(@PathVariable long id){
        return clientService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity <ApiResponse> update(@PathVariable long id, @RequestBody ClientDto dto){
        return clientService.update(id, dto.toEntity());
    }
    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){return clientService.getAll();}

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse> getByEmail(@PathVariable String email){
        return clientService.getByEmail(email);
    }

}
