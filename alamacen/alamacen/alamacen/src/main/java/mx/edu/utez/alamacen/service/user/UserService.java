package mx.edu.utez.alamacen.service.user;
import lombok.AllArgsConstructor;
import mx.edu.utez.alamacen.config.ApiResponse;
import mx.edu.utez.alamacen.model.category.CategoryBean;
import mx.edu.utez.alamacen.model.person.PersonBean;
import mx.edu.utez.alamacen.model.person.PersonRepository;
import mx.edu.utez.alamacen.model.product.ProductBean;
import mx.edu.utez.alamacen.model.rol.RolBean;
import mx.edu.utez.alamacen.model.rol.RolRepository;
import mx.edu.utez.alamacen.model.user.UserBean;
import mx.edu.utez.alamacen.model.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional(readOnly = true)
    public Optional<UserBean> findUserByUsername(String username){
        return userRepository.findFirstByUsername(username);
    }

}
