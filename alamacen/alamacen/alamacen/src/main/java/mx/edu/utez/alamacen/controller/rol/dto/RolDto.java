package mx.edu.utez.alamacen.controller.rol.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.alamacen.model.rol.RolBean;

@Data
@NoArgsConstructor
public class RolDto {

    private String rol;


    public RolBean toEntity(){
        return new RolBean(rol);
    }
}
