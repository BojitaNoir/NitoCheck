package mx.edu.utez.alamacen.controller.auth;

import mx.edu.utez.alamacen.config.ApiResponse;
import mx.edu.utez.alamacen.controller.auth.dto.SignDto;
import mx.edu.utez.alamacen.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }
    @PostMapping("/")
    public ResponseEntity<ApiResponse> signIn(@RequestBody SignDto signDto){
        return service.signIn(signDto.getUsername(), signDto.getPassword());
    }
}
