package com.unicaes.poo.domain.supplier;

import com.unicaes.poo.domain.supplier.dto.DtoSupplierList;
import com.unicaes.poo.domain.supplier.dto.DtoSuppliersResponse;
import com.unicaes.poo.domain.supplier.dto.DtoUpdateSupplier;
import com.unicaes.poo.infra.exceptions.QueryException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupplierService implements ISupplier {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public DtoSuppliersResponse createSupplier(DtoSupplierList dto) {
        try {
            Supplier supplier = new Supplier();
            supplier.setName(dto.name());
            supplier.setContact(dto.contact());
            supplier.setAddress(dto.address());
            supplier.setActive(true); // Siempre activo al crear

            Supplier savedSupplier = supplierRepository.save(supplier);
            return convertToResponseDto(savedSupplier);
        } catch (Exception e) {
            throw new QueryException("Error creating supplier: " + e.getMessage());
        }
    }

    @Override
    public DtoSuppliersResponse getSupplierById(Long id) {
        try {
            Supplier supplier = supplierRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id: " + id));
            return convertToResponseDto(supplier);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new QueryException("Error getting supplier: " + e.getMessage());
        }
    }

    @Override
    public void deactivateSupplier(long id) {
        try {
            Supplier supplier = supplierRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id: " + id));

            if (!supplier.isActive()) {
                throw new ValidationException("Supplier is already deactivated");
            }

            supplier.setActive(false);
            supplierRepository.save(supplier);
        } catch (EntityNotFoundException | ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new QueryException("Error deactivating supplier: " + e.getMessage());
        }
    }

    @Override
    public Page<DtoSupplierList> getSuppliersPage(Pageable pageable) {
        try {
            return supplierRepository.findAll(pageable)
                    .map(this::convertToListDto);
        } catch (Exception e) {
            throw new QueryException("Error getting suppliers list: " + e.getMessage());
        }
    }

    @Override
    public DtoSuppliersResponse updateSupplier(Long id, DtoUpdateSupplier dto) {
        try {
            Supplier supplier = supplierRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id: " + id));

            // Actualización parcial - solo campos no nulos
            if (dto.name() != null && !dto.name().isBlank()) {
                supplier.setName(dto.name());
            }
            if (dto.contact() != null && !dto.contact().isBlank()) {
                supplier.setContact(dto.contact());
            }
            if (dto.address() != null && !dto.address().isBlank()) {
                supplier.setAddress(dto.address());
            }

            Supplier updatedSupplier = supplierRepository.save(supplier);
            return convertToResponseDto(updatedSupplier);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new QueryException("Error updating supplier: " + e.getMessage());
        }
    }

    private DtoSuppliersResponse convertToResponseDto(Supplier supplier) {
        return new DtoSuppliersResponse(
                supplier.getSupplierId(),
                supplier.getName(),
                supplier.getContact(),
                supplier.getAddress(),
                supplier.isActive()
        );
    }

    private DtoSupplierList convertToListDto(Supplier supplier) {
        return new DtoSupplierList(
                supplier.getName(),
                supplier.getContact(),
                supplier.getAddress(),
                supplier.isActive()
        );
    }
}