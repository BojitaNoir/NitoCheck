package mx.edu.utez.alamacen.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserBean, Long> {

    Optional<UserBean> findFirstByUsername(String user);
    Optional<UserBean> findByPersonBeanCurp(String curp);

    Optional<UserBean> findByPersonBeanId(Long id);
    List<UserBean> findAllByStatus(Boolean status);

    @Query(value = "SELECT * FROM user u INNER JOIN person p ON u.person_id = p.id WHERE p.birthday BETWEEN :fechaUno AND : fechaDos",nativeQuery = true)
    List<UserBean> getUsers(@Param("fechaUno") String startDate,
                            @Param("fechaDos") String endDate);

    @Modifying
    @Query(value = "INSERT INTO user_roles (role_id,user_id) values(:roleId, :userId)",nativeQuery = true)
    int saveUserRole(@Param("role_id")Long roleId,@Param("userId") Long userId);

    @Query(value = "SELECT * FROM user_id FROM user_roles WHERE user_id = :userId AND role_id= :roleId",
    nativeQuery = true)
    Long getIdUsersRoles(@Param("role_id") Long userId, @Param("user_roles") long role_id);
}
