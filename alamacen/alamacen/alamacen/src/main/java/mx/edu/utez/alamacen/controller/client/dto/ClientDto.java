package mx.edu.utez.alamacen.controller.client.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.alamacen.model.client.ClientBean;

@NoArgsConstructor
@Data
public class ClientDto {
    private Long id;
    private String companyName;
    private String address;
    private String phoneNumber;
    private String email;


    public ClientBean toEntity(){return new ClientBean(companyName,address,phoneNumber,email );}
}
