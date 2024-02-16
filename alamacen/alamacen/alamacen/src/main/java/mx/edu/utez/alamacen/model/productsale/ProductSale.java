package mx.edu.utez.alamacen.model.productsale;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.alamacen.model.inventory.InventoryBean;
import mx.edu.utez.alamacen.model.product.ProductBean;
import mx.edu.utez.alamacen.model.ticket.TicketBean;

import java.util.Set;

@Entity
@Table(name = "product_sale")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String quantity;

    @Column(nullable = false)
    private Double total;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invetory_id", nullable = false)
    private InventoryBean inventoryBean;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ticket_id", nullable = false)
    private TicketBean ticketBean;


}
