package com.unicaes.poo.controller;

import com.unicaes.poo.domain.consumables.IConsumable;
import com.unicaes.poo.domain.consumables.dto.*;
import com.unicaes.poo.domain.consumableTypes.ConsumableTypes;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/consumable")
public class ConsumableController {

    private final IConsumable consumableService;

    public ConsumableController(IConsumable consumableService) {
        this.consumableService = consumableService;
    }

    /**
     * Crea un nuevo bien en el sistema
     * @param dto Datos del bien a crear (validados automáticamente)
     * @param uriBuilder Constructor para la URI de respuesta
     * @return Respuesta con el bien creado y cabecera Location
     */
    @PostMapping
    public ResponseEntity<DtoConsumableResponse> createConsuamble(
            @Valid @RequestBody DtoConsumableSave dto,
            UriComponentsBuilder uriBuilder) {
        DtoConsumableResponse response = consumableService.createConsumable(dto);
        URI uri = uriBuilder.path("/api/consumables/{id}").buildAndExpand(response.consumableId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    /**
     * Obtiene los detalles de un bien específico por su ID
     * @param id Identificador único del bien
     * @return Detalles completos del bien
     */
    @GetMapping("/{id}")
    public ResponseEntity<DtoConsumableResponse> getConsumableById(@PathVariable Long id) {
        return ResponseEntity.ok(consumableService.getConsumableById(id));
    }

    /**
     * Actualiza todos los campos editables de un bien existente
     * @param id Identificador del bien a actualizar
     * @param dto Datos de actualización (campos null no se actualizarán)
     * @return Bien actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<DtoConsumableResponse> updateConsumable(
            @PathVariable Long id,
            @Valid @RequestBody DtoConsumableUpdate dto) {
        return ResponseEntity.ok(consumableService.updateConsumable(id, dto));
    }

    /**
     * Cambia el estado activo/inactivo de un bien (toggle)
     * @param id Identificador del bien
     * @return Bien con estado actualizado
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<DtoConsumableResponse> toggleConsumableStatus(@PathVariable Long id) {
        return ResponseEntity.ok(consumableService.toggleConsumableStatus(id));
    }

    /**
     * Desactiva un bien (eliminación lógica)
     * @param id Identificador del bien a desactivar
     * @return Respuesta vacía con código 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateConsumable(@PathVariable Long id) {
        consumableService.deactivateConsumable(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtiene listado paginado de todos los bienes activos
     * @param pageable Configuración de paginación (page, size, sort)
     * @return Página de bienes con información básica
     */
    @GetMapping
    public ResponseEntity<Page<DtoConsumableList>> getAllConsumables(
            @PageableDefault(size = 100) Pageable pageable) {
        return ResponseEntity.ok(consumableService.getAllConsumable(pageable)); // Cambiado de getActiveGoods a getAllGoods
    }

    /**
     * Filtra bienes por tipo específico
     * @param type Tipo de bien según el enum GoodsTypes
     * @param pageable Configuración de paginación
     * @return Página de bienes del tipo especificado
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<Page<DtoConsumableList>> getConsumablesByType(
            @PathVariable ConsumableTypes type,
            @PageableDefault(size = 100) Pageable pageable) {
        return ResponseEntity.ok(consumableService.getConsumablesByType(type, pageable));
    }

    /**
     * Obtiene bienes con stock por debajo del umbral especificado
     * @param threshold Valor mínimo de stock para alerta (default: 5.0)
     * @return Lista de bienes con stock bajo
     */
    @GetMapping("/low-stock")
    public ResponseEntity<List<DtoConsumableResponse>> getLowStockGoods(
            @RequestParam(defaultValue = "5.0") Double threshold) {
        return ResponseEntity.ok(consumableService.getLowStockConsumables(threshold));
    }

    /**
     * Busca bienes cuyo nombre contenga el texto especificado
     * @param name Fragmento del nombre a buscar (case insensitive)
     * @param pageable Configuración de paginación
     * @return Página de bienes que coinciden con la búsqueda
     */
    @GetMapping("/search")
    public ResponseEntity<Page<DtoConsumableList>> searchConsumablesByName(
            @RequestParam String name,
            @PageableDefault(size = 100) Pageable pageable) {
        return ResponseEntity.ok(consumableService.searchConsumablesByName(name, pageable));
    }
}