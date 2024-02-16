package mx.edu.utez.alamacen.service.product;

import lombok.AllArgsConstructor;
import mx.edu.utez.alamacen.config.ApiResponse;
import mx.edu.utez.alamacen.model.category.CategoryBean;
import mx.edu.utez.alamacen.model.category.CategoryRepository;
import mx.edu.utez.alamacen.model.person.PersonBean;
import mx.edu.utez.alamacen.model.person.PersonRepository;
import mx.edu.utez.alamacen.model.product.ProductBean;
import mx.edu.utez.alamacen.model.product.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> save(ProductBean productBean) {
        Optional<ProductBean> foundProduct = repository.findById(productBean.getId());
        if (foundProduct.isPresent())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                    "Registro duplicado"), HttpStatus.BAD_REQUEST);
        if (productBean.getCategoryBean() != null){
            Optional<CategoryBean> foundCategory = categoryRepository.findById(productBean.getCategoryBean().getId());
            if (foundCategory.isPresent())
                return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(productBean), HttpStatus.OK,
                        "El producto se guardo exitosamente"), HttpStatus.OK);
            else
                new ResponseEntity<>(new ApiResponse(categoryRepository.saveAndFlush(productBean.getCategoryBean()),
                        HttpStatus.OK, "Se registra"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(productBean), HttpStatus.OK,
                "El producto se guardo exitosamente"), HttpStatus.OK);
    }
}
