package com.unicaes.poo.controller;

import com.unicaes.poo.domain.goods.IGood;
import com.unicaes.poo.domain.goods.dto.*;
import com.unicaes.poo.domain.goodsTypes.GoodsTypes;
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
@RequestMapping("/api/goods")
public class GoodController {

    private final IGood goodService;

    public GoodController(IGood goodService) {
        this.goodService = goodService;
    }

    /**
     * Crea un nuevo bien en el sistema
     * @param dto Datos del bien a crear (validados automáticamente)
     * @param uriBuilder Constructor para la URI de respuesta
     * @return Respuesta con el bien creado y cabecera Location
     */
    @PostMapping
    public ResponseEntity<DtoGoodResponse> createGood(
            @Valid @RequestBody DtoGoodSave dto,
            UriComponentsBuilder uriBuilder) {
        DtoGoodResponse response = goodService.createGood(dto);
        URI uri = uriBuilder.path("/api/goods/{id}").buildAndExpand(response.goodId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    /**
     * Obtiene los detalles de un bien específico por su ID
     * @param id Identificador único del bien
     * @return Detalles completos del bien
     */
    @GetMapping("/{id}")
    public ResponseEntity<DtoGoodResponse> getGoodById(@PathVariable Long id) {
        return ResponseEntity.ok(goodService.getGoodById(id));
    }

    /**
     * Actualiza todos los campos editables de un bien existente
     * @param id Identificador del bien a actualizar
     * @param dto Datos de actualización (campos null no se actualizarán)
     * @return Bien actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<DtoGoodResponse> updateGood(
            @PathVariable Long id,
            @Valid @RequestBody DtoGoodUpdate dto) {
        return ResponseEntity.ok(goodService.updateGood(id, dto));
    }

    /**
     * Cambia el estado activo/inactivo de un bien (toggle)
     * @param id Identificador del bien
     * @return Bien con estado actualizado
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<DtoGoodResponse> toggleGoodStatus(@PathVariable Long id) {
        return ResponseEntity.ok(goodService.toggleGoodStatus(id));
    }

    /**
     * Desactiva un bien (eliminación lógica)
     * @param id Identificador del bien a desactivar
     * @return Respuesta vacía con código 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateGood(@PathVariable Long id) {
        goodService.deactivateGood(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtiene listado paginado de todos los bienes activos
     * @param pageable Configuración de paginación (page, size, sort)
     * @return Página de bienes con información básica
     */
    @GetMapping
    public ResponseEntity<Page<DtoGoodList>> getAllGoods(
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(goodService.getAllGoods(pageable)); // Cambiado de getActiveGoods a getAllGoods
    }

    /**
     * Filtra bienes por tipo específico
     * @param type Tipo de bien según el enum GoodsTypes
     * @param pageable Configuración de paginación
     * @return Página de bienes del tipo especificado
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<Page<DtoGoodList>> getGoodsByType(
            @PathVariable GoodsTypes type,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(goodService.getGoodsByType(type, pageable));
    }

    /**
     * Obtiene bienes con stock por debajo del umbral especificado
     * @param threshold Valor mínimo de stock para alerta (default: 5.0)
     * @return Lista de bienes con stock bajo
     */
    @GetMapping("/low-stock")
    public ResponseEntity<List<DtoGoodResponse>> getLowStockGoods(
            @RequestParam(defaultValue = "5.0") Double threshold) {
        return ResponseEntity.ok(goodService.getLowStockGoods(threshold));
    }

    /**
     * Busca bienes cuyo nombre contenga el texto especificado
     * @param name Fragmento del nombre a buscar (case insensitive)
     * @param pageable Configuración de paginación
     * @return Página de bienes que coinciden con la búsqueda
     */
    @GetMapping("/search")
    public ResponseEntity<Page<DtoGoodList>> searchGoodsByName(
            @RequestParam String name,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(goodService.searchGoodsByName(name, pageable));
    }
}