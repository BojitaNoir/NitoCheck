package mx.edu.utez.alamacen.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.alamacen.model.person.PersonBean;
import mx.edu.utez.alamacen.model.rol.RolBean;
import mx.edu.utez.alamacen.model.ticket.TicketBean;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(length = 50, nullable = false)
    private String user;
    @Column(length = 50, nullable = false)
    private String password;
    @Column(columnDefinition = "TEXT")
    private String avatar;
    @Column(columnDefinition = "TIMESTAMP DEFAULT NOW()",insertable = false)
    private LocalDateTime creatAt;

    @Column(columnDefinition = "BOOL DEFAULT true")
    private Boolean status;
    @Column(columnDefinition = "BOOL DEFAULT true")
    private Boolean blocked;
    @Column(columnDefinition = "BOOL DEFAULT true")
    private String token;

    @JsonIgnore
    @ManyToMany
    @JoinColumn(name = "rol_id")
    private Set<RolBean> roles;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonBean personBean;



    @JsonIgnore
    @OneToMany(mappedBy = "userBean")
    private Set<TicketBean> ticketBean;

    public UserBean(String user, String password, PersonBean personBean, Set<RolBean> roles) {
        this.user = user;
        this.password = password;
        this.personBean = personBean;
        this.status = true;
        this.creatAt=LocalDateTime.now();
        this.blocked = true;
        this.roles =roles;
    }

}
