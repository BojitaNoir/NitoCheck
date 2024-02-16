package mx.edu.utez.alamacen.controller.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.alamacen.model.person.PersonBean;
import mx.edu.utez.alamacen.model.rol.RolBean;
import mx.edu.utez.alamacen.model.user.UserBean;

@Data
@NoArgsConstructor
public class UserDto {

    private long id;
    private String user;
    private String password;


    private PersonBean personBean;

    private RolBean roles;



}
