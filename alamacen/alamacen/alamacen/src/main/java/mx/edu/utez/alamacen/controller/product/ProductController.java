package mx.edu.utez.alamacen.controller.product;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import mx.edu.utez.alamacen.config.ApiResponse;
import mx.edu.utez.alamacen.controller.person.dto.PersonDto;
import mx.edu.utez.alamacen.controller.product.dto.ProductDto;
import mx.edu.utez.alamacen.service.person.PersonService;
import mx.edu.utez.alamacen.service.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/almacen/product")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> register(@RequestBody ProductDto dto){
        return  service.save(dto.toEntity());
    }


}
