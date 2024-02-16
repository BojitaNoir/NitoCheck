package mx.edu.utez.alamacen.model.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.alamacen.model.user.UserBean;

import java.time.LocalDate;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PersonBean {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false)
    private String fullname;

    @Column(length = 50, nullable = false)
    private String surname;

    @Column(length = 50, nullable = false)
    private String lastname;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate birthday;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String phoneNumber;

    @Column(length = 18, nullable = false)
    private String curp;

    @JsonIgnore
    @OneToOne(mappedBy = "personBean")
    private UserBean userBean;


    public PersonBean(String fullname, String surname, String lastname, LocalDate birthday, String email, String phoneNumber, String curp) {
        this.fullname = fullname;
        this.surname = surname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.curp = curp;
    }


}