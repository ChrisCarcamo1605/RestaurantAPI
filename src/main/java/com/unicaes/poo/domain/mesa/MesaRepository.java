package com.unicaes.poo.domain.mesa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MesaRepository extends JpaRepository<Mesa, Long> {

    List<Mesa> findByActivoIsTrue();

}
