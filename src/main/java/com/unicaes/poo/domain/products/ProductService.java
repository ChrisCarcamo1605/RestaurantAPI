package com.unicaes.poo.domain.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {

        return productRepository.findAll();
    }

    public Product findById(long id) {
        return productRepository.findById(id).get();
    }

    public Product save(DtoSaveProduct dto) {
        Product product = new Product();
        product.name = dto.name();
        product.priceCost = dto.priceCost();
        product.priceSell = dto.priceSell();
        product.measurementUnit = dto.measurementUnit();
        product.description = dto.description();
        product.measurementUnit = dto.measurementUnit();

        return productRepository.save(product);
    }

    public Page<DtoProductsList> getProductsList(Pageable pageable) {
        return productRepository.findByActiveTrue(pageable).map(DtoProductsList::new);
    }

    public Product updateProduct(DtoUpdateProduct dto) {

        var product = productRepository.getReferenceById(dto.id());

        if (dto.name() != null) {
            product.name = dto.name();
        }
        if (dto.priceCost() != null) {
            product.priceCost = dto.priceCost();
        }
        if (dto.priceSell() != null) {
            product.priceSell = dto.priceSell();
        }
        if (dto.measurementUnit() != null) {
            product.measurementUnit = dto.measurementUnit();
        }
        if (dto.description() != null) {
            product.description = dto.description();
        }
        return productRepository.save(product);
    }
}
