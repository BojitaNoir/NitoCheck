package mx.edu.utez.alamacen.model.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRespository extends JpaRepository<InventoryBean, Long> {
}
