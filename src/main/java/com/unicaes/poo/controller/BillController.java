package com.unicaes.poo.controller;

import com.unicaes.poo.domain.bill.Bill;
import com.unicaes.poo.domain.bill.IBill;
import com.unicaes.poo.domain.bill.BillRepository;
import com.unicaes.poo.domain.bill.IBillService;
import com.unicaes.poo.domain.bill.dto.DtoBillList;
import com.unicaes.poo.domain.bill.dto.DtoBillResponse;
import com.unicaes.poo.domain.bill.dto.DtoBillSave;
import com.unicaes.poo.domain.cliente.Cliente;
import com.unicaes.poo.domain.cliente.ClienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bill")
public class BillController {


    @Autowired
    private IBillService billService;


    @GetMapping
    public ResponseEntity<List<DtoBillList>> getBills() {
        var bills = billService.getAll();
        return ResponseEntity.ok(bills);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<DtoBillResponse> createBill(@RequestBody @Valid DtoBillSave bill, UriComponentsBuilder ucBuilder) {

        var newBill = billService.save(bill);

        System.out.println(newBill.emissionDate()+"HOY" + newBill.id());
        URI location = ucBuilder.path("bill").buildAndExpand(newBill.id()).toUri();
        return ResponseEntity.created(location).body(newBill);
    }


}


