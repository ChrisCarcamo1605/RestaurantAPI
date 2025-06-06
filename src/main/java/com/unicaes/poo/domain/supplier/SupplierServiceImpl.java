package com.unicaes.poo.domain.supplier;

import com.unicaes.poo.domain.supplier.dto.DtoSupplierList;
import com.unicaes.poo.domain.supplier.dto.DtoSupplierSave;
import com.unicaes.poo.domain.supplier.dto.DtoSuppliersResponse;
import com.unicaes.poo.domain.supplier.dto.DtoUpdateSupplier;
import com.unicaes.poo.interfaces.supplier.Supplier;
import com.unicaes.poo.repository.SupplierRepository;
import com.unicaes.poo.infra.exceptions.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements Supplier {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public DtoSuppliersResponse createSupplier(DtoSupplierSave dto) {
        try {
            com.unicaes.poo.domain.supplier.Supplier supplier = new com.unicaes.poo.domain.supplier.Supplier();
            supplier.setName(dto.name());
            supplier.setContact(dto.contact());
            supplier.setAddress(dto.address());
            supplier.setActive(true);

            com.unicaes.poo.domain.supplier.Supplier savedSupplier = supplierRepository.save(supplier);
            return convertToResponseDto(savedSupplier);
        } catch (Exception e) {
            throw new QueryException("Error creating supplier: " + e.getMessage());
        }
    }

    @Override
    public DtoSuppliersResponse getSupplierById(Long id) {
        try {
            com.unicaes.poo.domain.supplier.Supplier supplier = supplierRepository.findById(id)
                    .orElseThrow(() -> new QueryException("Supplier not found with id: " + id));
            return convertToResponseDto(supplier);

        } catch (Exception e) {
            throw new QueryException("Error getting supplier: " + e.getMessage());
        }
    }

    @Override
    public void deactivateSupplier(long id) {
        try {
            com.unicaes.poo.domain.supplier.Supplier supplier = supplierRepository.findById(id)
                    .orElseThrow(() -> new QueryException("Supplier not found with id: " + id));

            if (!supplier.isActive()) {
                throw new QueryException("Supplier is already deactivated");
            }

            supplier.setActive(false);
            supplierRepository.save(supplier);

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
            com.unicaes.poo.domain.supplier.Supplier supplier = supplierRepository.findById(id)
                    .orElseThrow(() -> new QueryException("Supplier not found with id: " + id));

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

            com.unicaes.poo.domain.supplier.Supplier updatedSupplier = supplierRepository.save(supplier);
            return convertToResponseDto(updatedSupplier);
        } catch (Exception e) {
            throw new QueryException("Error updating supplier: " + e.getMessage());
        }
    }

    private DtoSuppliersResponse convertToResponseDto(com.unicaes.poo.domain.supplier.Supplier supplier) {
        return new DtoSuppliersResponse(
                supplier.getSupplierId(),
                supplier.getName(),
                supplier.getContact(),
                supplier.getAddress(),
                supplier.isActive()
        );
    }

    private DtoSupplierList convertToListDto(com.unicaes.poo.domain.supplier.Supplier supplier) {
        return new DtoSupplierList(
                supplier.getSupplierId(),
                supplier.getName(),
                supplier.getContact(),
                supplier.getAddress(),
                supplier.isActive()
        );
    }
}
