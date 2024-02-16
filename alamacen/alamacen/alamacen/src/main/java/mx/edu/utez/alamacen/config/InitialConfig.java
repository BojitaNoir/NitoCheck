package mx.edu.utez.alamacen.config;

import mx.edu.utez.alamacen.model.person.PersonBean;
import mx.edu.utez.alamacen.model.person.PersonRepository;
import mx.edu.utez.alamacen.model.rol.RolBean;
import mx.edu.utez.alamacen.model.rol.RolRepository;
import mx.edu.utez.alamacen.model.user.UserBean;
import mx.edu.utez.alamacen.model.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Configuration
public class InitialConfig implements CommandLineRunner {

    private final RolRepository rolRepository;

    private final UserRepository userRepository;

    private  final PersonRepository personRepository;

    //private final PasswordEncoder encoder;

    public InitialConfig(RolRepository rolRepository, UserRepository userRepository, PersonRepository personRepository) {
        this.rolRepository = rolRepository;
        this.userRepository = userRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        RolBean adminrole = getOrSaveRole(new RolBean("ADMIN_ROLE"));
        getOrSaveRole(new RolBean("USER_ROLE"));
        getOrSaveRole(new RolBean("CLIENT_ROLE"));

        PersonBean person = getOrSavePerson(new PersonBean("AYALA", "Jonathan", "SI", LocalDate.of(2004,9,13),"AA","777085090","a"));


    }
    @Transactional
    public RolBean getOrSaveRole (RolBean rol){
        Optional<RolBean> founRole = rolRepository.findByRol(rol.getRol());
        return founRole.orElseGet(()-> rolRepository.saveAndFlush(rol));
    }

    @Transactional
    public PersonBean getOrSavePerson (PersonBean person){
        Optional<PersonBean> founPerson = personRepository.findByCurp(person.getCurp());
        return founPerson.orElseGet(()-> personRepository.saveAndFlush(person));
    }

    @Transactional
    public UserBean getOrSaveUser (UserBean user){
        Optional<UserBean> foundUser = userRepository.findFirstByUsername(user.getUser());
        return foundUser.orElseGet(()-> userRepository.saveAndFlush(user));
    }


    /*
    @Transactional
    public void saveUserRol(Long id, Long role_id){
        Long userRoleId = userRepository.getIdUserRoles(id, role_id);
        if (userRoleId == null)
            userRepository.saveUserRol(id, role_id);
    }*/


}