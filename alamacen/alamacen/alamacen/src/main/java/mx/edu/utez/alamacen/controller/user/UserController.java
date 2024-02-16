package mx.edu.utez.alamacen.controller.user;

import lombok.AllArgsConstructor;
import mx.edu.utez.alamacen.config.ApiResponse;
import mx.edu.utez.alamacen.controller.category.dto.CategoryDto;
import mx.edu.utez.alamacen.controller.user.dto.UserDto;
import mx.edu.utez.alamacen.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/almacen/user")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/")
    public ResponseEntity<ApiResponse> register(@RequestBody UserDto userDto) {
        return userService.save(userDto.toEntity());
    }


    /*
        @PostMapping("/")
        public ResponseEntity<ApiResponse> register(@RequestBody UserDto userDto) {
            return userService.save(userDto.toEntity());

        }


     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable long id, @RequestBody UserDto userDto) {
        return userService.update(id, userDto.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable long id) {
        return userService.delete(id);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll() {
        return userService.getAll();
    }
    @GetMapping("/user/{user}")
    public ResponseEntity<ApiResponse> getByUser(@PathVariable String user) {
        return userService.getByUser(user);
    }


}