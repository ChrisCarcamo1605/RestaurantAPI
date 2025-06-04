package com.unicaes.poo.domain.table;

import com.unicaes.poo.domain.table.dto.*;
import com.unicaes.poo.interfaces.table.ITableService;
import com.unicaes.poo.repository.TableRepository;
import com.unicaes.poo.infra.exceptions.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService implements ITableService {

    @Autowired
    private TableRepository mesaRepository;

    @Override
    public List<DtoTableList> findAll() {
        try {
            return mesaRepository.findByActiveIsTrue()
                    .stream()
                    .map(DtoTableList::fromEntity)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new QueryException("Error al obtener mesas: " + e.getMessage());
        }
    }

    @Override
    public DtoTableResponse findById(Long id) {
        try {
            Table mesa = mesaRepository.findById(id)
                    .orElseThrow(() -> new QueryException("Mesa no encontrada con id: " + id));
            return DtoTableResponse.fromEntity(mesa);
        } catch (Exception e) {
            throw new QueryException("Error al buscar mesa por id: " + e.getMessage());
        }
    }

    @Override
    public DtoTableResponse save(DtoTableSave dto) {
        try {
            Table mesa = dto.toEntity();
            return DtoTableResponse.fromEntity(mesaRepository.save(mesa));
        } catch (Exception e) {
            throw new QueryException("Error al guardar la mesa: " + e.getMessage());
        }
    }

    @Override
    public DtoTableResponse update(DtoTableUpdate dto) {
        try {
            Table mesa = mesaRepository.getReferenceById(dto.id());

            if (dto.capacity() != null) {
                mesa.setCapacity(dto.capacity());
            }

            if (dto.number() != null) {
                mesa.setNumber(dto.number());
            }

            return DtoTableResponse.fromEntity(mesaRepository.save(mesa));
        } catch (Exception e) {
            throw new QueryException("Error al actualizar la mesa: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            var mesa = mesaRepository.getReferenceById(id);
            mesa.setActive(Boolean.FALSE);
            mesaRepository.save(mesa);
        } catch (Exception e) {
            throw new QueryException("Error al eliminar (desactivar) la mesa: " + e.getMessage());
        }
    }
}
