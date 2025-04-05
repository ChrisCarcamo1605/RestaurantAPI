package com.unicaes.poo.domain.supplier;

import com.unicaes.poo.domain.supplier.dto.DtoSupplierList;
import com.unicaes.poo.domain.supplier.dto.DtoSuppliersResponse;
import com.unicaes.poo.domain.supplier.dto.DtoUpdateSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService implements ISupplier {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public DtoSuppliersResponse createSupplier(DtoSupplierList dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.name());
        supplier.setContact(dto.contact());
        supplier.setAddress(dto.address());
        supplier.setActive(dto.active());

        Supplier savedSupplier = supplierRepository.save(supplier);
        return convertToResponseDto(savedSupplier);
    }

    @Override
    public DtoSuppliersResponse getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        return convertToResponseDto(supplier);
    }

    @Override
    public void deleteSupplier(long id) {
        var supplier = supplierRepository.getReferenceById(id);
        supplier.setActive(false);
        supplierRepository.save(supplier);
    }

    @Override
    public DtoSuppliersResponse activeSupplier(Long id) {
        var supplier = supplierRepository.getReferenceById(id);
        supplier.setActive(true);
       return convertToResponseDto(supplierRepository.save(supplier));
    }

    @Override
    public Page<DtoSupplierList> getSuppliersPage(Pageable pageable) {
        return supplierRepository.findAll(pageable)
                .map(this::convertToListDto);
    }

    @Override
    public DtoSuppliersResponse deleteSupplier(Long id, DtoUpdateSupplier dto) throws RuntimeException {

        Supplier supplier = supplierRepository.getReferenceById(id);

        supplier.setName(dto.name());

        if (dto.contact() != null) supplier.setContact(dto.contact());

        if (dto.contact() != null) {
            supplier.setAddress(dto.address());
        }

        if (dto.address() != null) {
            supplier.setAddress(dto.address());
        }
        System.out.println("Depurando pa " + supplier.getName());

        Supplier updatedSupplier = supplierRepository.save(supplier);
        return convertToResponseDto(updatedSupplier);
    }

    @Override
    public DtoSuppliersResponse toggleSupplierStatus(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));

        // Cambia el estado (true->false o false->true)
        supplier.setActive(!supplier.isActive());

        Supplier toggledSupplier = supplierRepository.save(supplier);
        return convertToResponseDto(toggledSupplier);
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