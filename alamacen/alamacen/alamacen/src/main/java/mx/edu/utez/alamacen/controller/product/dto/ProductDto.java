package mx.edu.utez.alamacen.controller.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.alamacen.model.category.CategoryBean;
import mx.edu.utez.alamacen.model.product.ProductBean;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;

    private String name;

    private String trademark;

    private String model;

    private Double price;

    private CategoryBean categoryBean;

    public ProductBean toEntity(){
        if (categoryBean ==null)
            return new ProductBean(name, trademark, model, price);

        return new ProductBean(id, name, trademark, model, price, categoryBean);
    }
}
