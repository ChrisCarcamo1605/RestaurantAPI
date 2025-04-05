package com.unicaes.poo.domain.products;

import com.unicaes.poo.domain.products.dto.DtoProductsList;
import com.unicaes.poo.domain.products.dto.DtoSaveProduct;
import com.unicaes.poo.domain.products.dto.DtoUpdateProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService implements IProduct {

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
        product.setName(dto.name());
        product.priceCost = dto.priceCost();
        product.priceSell = dto.priceSell();
        product.measurementUnit = dto.measurementUnit();
        product.setDescription(dto.description());
        product.measurementUnit = dto.measurementUnit();

        return productRepository.save(product);
    }

    public Page<DtoProductsList> getProductsList(Pageable pageable) {
        return productRepository.findByActiveTrue(pageable).map(DtoProductsList::new);
    }

    public Product updateProduct(DtoUpdateProduct dto) {

        var product = productRepository.getReferenceById(dto.id());
        System.out.println("de dto: " + dto.priceCost());

        if (dto.name() != null) {
            product.setName(dto.name());
        }

        if (dto.priceCost() != null ) {
            product.setPriceCost( dto.priceCost());
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


        var a = productRepository.save(product);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(a.getId()+ a.getName() + a.getPriceCost() + a.getPriceSell() + a.getMeasurementUnit());
        return a;
    }
}
