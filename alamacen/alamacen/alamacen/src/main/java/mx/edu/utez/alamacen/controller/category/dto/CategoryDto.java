package mx.edu.utez.alamacen.controller.category.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.alamacen.model.category.CategoryBean;
import mx.edu.utez.alamacen.model.client.ClientBean;

@NoArgsConstructor
@Data
public class CategoryDto {

    private Long id;
    private String nombre;
    private String codigo;

    public CategoryBean toEntity(){return new CategoryBean(nombre, codigo);

    }
}
