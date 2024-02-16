package mx.edu.utez.alamacen.model.rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RolRepository extends JpaRepository<RolBean, Long> {

    Optional<RolBean> findByRol(String rol);

    @Modifying
    @Query(value = "INSERT INTO user_roles (role_id,user_id) values(:roleId, :userId)",nativeQuery = true)
    int saveUserRole(@Param("role_id")Long roleId,@Param("userId") Long userId);

}

