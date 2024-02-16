package mx.edu.utez.alamacen.model.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.alamacen.model.product.ProductBean;

import java.util.Set;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40, nullable = false)
    private String nombre;
    @Column(length = 10)
    private String codigo;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryBean")
    private Set<ProductBean> productBeans;

    public CategoryBean(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }
}
