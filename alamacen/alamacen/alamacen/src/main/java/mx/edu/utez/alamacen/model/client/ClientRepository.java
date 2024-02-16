package mx.edu.utez.alamacen.model.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientBean, Long> {
    Optional<ClientBean> findByEmail(String email);
}
