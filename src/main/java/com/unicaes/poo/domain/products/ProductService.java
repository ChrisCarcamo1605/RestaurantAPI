package com.unicaes.poo.domain.products;

import com.unicaes.poo.domain.products.dto.DtoProductResponse;
import com.unicaes.poo.domain.products.dto.DtoSaveProduct;
import com.unicaes.poo.domain.products.dto.DtoTypeResponse;
import com.unicaes.poo.domain.products.dto.DtoUpdateProduct;
import com.unicaes.poo.domain.products.interfaces.IProductService;
import com.unicaes.poo.domain.products.interfaces.ProductRepository;
import com.unicaes.poo.domain.products.interfaces.ProductTypeRepository;
import com.unicaes.poo.infra.exceptions.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository typeRepository;

    @Override
    public DtoProductResponse save(DtoSaveProduct dto) {
        try {
            var type = typeRepository.getReferenceById(dto.type());

            Product product = new Product();
            product.setName(dto.name());
            product.setProductType(type);
            product.setPriceCost(dto.priceCost());
            product.setPriceSell(dto.priceSell());
            product.setMeasurementUnit(dto.measurementUnit());
            product.setDescription(dto.description());

            return DtoProductResponse.fromEntity(productRepository.save(product));
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public List<DtoProductResponse> getProductsList(Pageable pageable) {
        return productRepository.findByActiveTrue(pageable).stream()
                .map(DtoProductResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<Product> findPriceLess(double price) {
        return productRepository.findPriceLess(price);
    }

    @Override
    public DtoProductResponse updateProduct(DtoUpdateProduct dto) {
        try {
            var product = productRepository.getReferenceById(dto.id());

            if (product == null) {
                throw new QueryException("El id ingresado no hace referencia a ningún producto");
            }

            if (dto.name() != null) {
                product.setName(dto.name());
            }

            if (dto.productType() != 0) {
                product.setProductType(typeRepository.getReferenceById(dto.productType()));
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
            return DtoProductResponse.fromEntity(productRepository.save(product));

        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public DtoTypeResponse save(String productName) {

        try {
            var type = new ProductType();
            type.setName(productName);
            return DtoTypeResponse.fromEntiy(typeRepository.save(type));

        } catch (QueryException e) {
            throw new QueryException(e.getMessage());
        }

    }

    @Override
    public DtoTypeResponse update(long id, String productName) {
        try {
            var type = typeRepository.getReferenceById(id);
            type.setName(productName);
            return DtoTypeResponse.fromEntiy(typeRepository.save(type));
        } catch (QueryException e) {
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {

        try {
            typeRepository.delete(typeRepository.getReferenceById(id));
        } catch (QueryException e) {
            throw new QueryException(e.getMessage());
        }

    }

    @Override
    public List<DtoTypeResponse> findAll() {
        try {
            return typeRepository.findAll().stream()
                    .map(DtoTypeResponse::fromEntiy).collect(Collectors.toList());
        } catch (QueryException e) {
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public void deleteProduct(long id) {
        try {
            var product = productRepository.getReferenceById(id);
            product.setActive(false);
            productRepository.save(product);
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }
    }


}
