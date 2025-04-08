package com.unicaes.poo.controller;

import com.unicaes.poo.domain.bill.IBillDetails;
import com.unicaes.poo.domain.bill.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill-details")
@RequiredArgsConstructor
public class BillDetailsController {

    private final IBillDetails billDetailsService;

    @GetMapping
    public List<DtoBillDetailsList> getAllActive() {
        return billDetailsService.getAllActive();
    }

    @GetMapping("/{id}")
    public DtoBillDetailsResponse getById(@PathVariable Long id) {
        return billDetailsService.getById(id);
    }

    @PostMapping
    public DtoBillDetailsResponse create(@RequestBody DtoBillDetailsSave dto) {
        return billDetailsService.save(dto);
    }

    @PutMapping("/{id}")
    public DtoBillDetailsResponse update(
            @PathVariable Long id,
            @RequestBody DtoBillDetailsUpdate dto) {
        return billDetailsService.update(dto.withId(id));
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        billDetailsService.deactivate(id);
    }
}