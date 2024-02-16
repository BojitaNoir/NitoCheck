package mx.edu.utez.alamacen.model.ticket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.alamacen.model.client.ClientBean;
import mx.edu.utez.alamacen.model.productsale.ProductSale;
import mx.edu.utez.alamacen.model.user.UserBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate saleDate;

    @Column(nullable = false)
    private Integer folio;

    @Column(columnDefinition = "TIME", nullable = false)
    private LocalTime saleHour;

    @Column(nullable = false)
    private double totPay;

    @Column(nullable = false)
    private Integer totProduct;


    @OneToMany(mappedBy = "ticketBean")
    private Set<ProductSale> productSales;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cliente_id", nullable = false)
    private ClientBean clientBean;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    private UserBean userBean;

}
