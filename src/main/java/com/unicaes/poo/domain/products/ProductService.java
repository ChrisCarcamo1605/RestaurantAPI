package com.unicaes.poo.domain.products;

import com.unicaes.poo.domain.products.dto.DtoProductsList;
import com.unicaes.poo.domain.products.dto.DtoSaveProduct;
import com.unicaes.poo.domain.products.dto.DtoUpdateProduct;
import com.unicaes.poo.infra.exceptions.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService implements IProduct {

    @Autowired
    private ProductRepository productRepository;


    public Product save(DtoSaveProduct dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setPriceCost(dto.priceCost());
        product.setPriceSell(dto.priceSell());
        product.setMeasurementUnit(dto.measurementUnit());
        product.setDescription(dto.description());

        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new QueryException("Producto ya existe");
        }
    }

    public Page<DtoProductsList> getProductsList(Pageable pageable) {
        return productRepository.findByActiveTrue(pageable).map(DtoProductsList::new);
    }

    public Product updateProduct(DtoUpdateProduct dto) {


        var product = productRepository.getReferenceById(dto.id());

        if (product == null) {
            throw new QueryException("El id ingresado no hace referencia a ningún producto");
        }


        if (dto.name() != null) {
            product.setName(dto.name());
        }

        if (dto.priceCost() != null) {
            product.setPriceCost(dto.priceCost());
        }

        if (dto.priceSell() != null && dto.priceSell().compareTo(BigDecimal.ZERO) > 0) {
            product.setPriceSell(dto.priceSell());
        }

        if (dto.measurementUnit() != null) {
            product.setMeasurementUnit(dto.measurementUnit());
        }

        if (dto.description() != null) {
            product.setDescription(dto.description());
        }
        return productRepository.save(product);
    }

    public void deleteProduct(long id) {
        var product = productRepository.getReferenceById(id);
        product.setActive(false);
        productRepository.save(product);
    }
}
