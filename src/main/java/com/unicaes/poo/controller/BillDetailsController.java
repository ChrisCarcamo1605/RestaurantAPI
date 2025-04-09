package com.unicaes.poo.controller;

import com.unicaes.poo.domain.bill.IBillService;
import com.unicaes.poo.domain.bill.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bill-details")
@RequiredArgsConstructor
public class BillDetailsController {
    @Autowired
    private IBillService billService;

    @GetMapping
    public List<DtoBillDetailsList> getAllActive() {
        return billService.getAllActive();
    }

    @GetMapping("/{id}")
    public DtoBillDetailsResponse getById(@PathVariable Long id) {
        return billService.getById(id);
    }

    @PostMapping
    public ResponseEntity<DtoBillDetailsResponse> create(@RequestBody @Valid DtoBillDetailsSave dto,
                                                         UriComponentsBuilder uriBuilder) {

        URI uri = uriBuilder.path("/bill-details/{id}").buildAndExpand(dto.billId()).toUri();
        return ResponseEntity.created(uri).body(billService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoBillDetailsResponse> update(
            @PathVariable Long id,
            @RequestBody DtoBillDetailsUpdate dto) {
        return ResponseEntity.accepted().body(billService.update(dto.withId(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deactivate(@PathVariable Long id) {
        billService.deactivate(id);

        return ResponseEntity.noContent().build();
    }
}