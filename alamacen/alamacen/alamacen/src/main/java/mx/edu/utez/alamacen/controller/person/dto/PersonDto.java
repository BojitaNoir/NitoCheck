package mx.edu.utez.alamacen.controller.person.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.alamacen.model.person.PersonBean;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class PersonDto {


    private long id;

    private String fullname;

    private String surname;

    private String lastname;

    private LocalDate birthday;

    private String email;

    private String phoneNumber;

    private String curp;

    public PersonBean toEntity(){
        return new PersonBean( fullname, surname, lastname, birthday, email, phoneNumber, curp);
    }
}
