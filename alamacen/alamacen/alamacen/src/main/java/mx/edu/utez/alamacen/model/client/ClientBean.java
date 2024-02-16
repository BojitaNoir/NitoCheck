package mx.edu.utez.alamacen.model.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.alamacen.model.ticket.TicketBean;

import java.util.Set;

@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ClientBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String companyName;
    @Column(length = 50, nullable = false)
    private String address;
    @Column(length = 10, nullable = false)
    private String phoneNumber;
    @Column(length = 150, nullable = false)
    private String email;

    public ClientBean(String companyName, String address, String phoneNumber, String email) {
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @OneToMany(mappedBy = "clientBean")
    private Set<TicketBean> ticketBeans;
}
