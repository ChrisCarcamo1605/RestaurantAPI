package com.unicaes.poo.domain.reservas;

import com.unicaes.poo.domain.reservas.dto.DtoSaveReserva;
import com.unicaes.poo.domain.reservas.dto.DtoUpdateReserva;
import com.unicaes.poo.domain.reservas.dto.DtoReservaResponse;
import com.unicaes.poo.infra.exceptions.QueryException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService implements IReserva {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public DtoReservaResponse saveReserva(DtoSaveReserva dtoSaveReserva) {
        Reserva reserva = new Reserva();
        reserva.setMesaId(dtoSaveReserva.mesaId());
        reserva.setCliente(dtoSaveReserva.cliente());
        reserva.setFechaHora(dtoSaveReserva.fechaHora());
        reserva.setMontoReserva(dtoSaveReserva.montoReserva());
        reserva.setActivo(true);

        reserva = reservaRepository.save(reserva);

        return mapToDto(reserva);
    }

    @Override
    public List<DtoReservaResponse> getAllReservas() {
        return reservaRepository.findByActivoTrue()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DtoReservaResponse getReservaById(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new QueryException("Reserva no encontrada"));

        return mapToDto(reserva);
    }

    @Override
    public DtoReservaResponse updateReserva(Long id, DtoUpdateReserva dtoUpdateReserva) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new QueryException("Reserva no encontrada"));

        reserva.setMesaId(dtoUpdateReserva.mesaId());
        reserva.setCliente(dtoUpdateReserva.cliente());
        reserva.setFechaHora(dtoUpdateReserva.fechaHora());
        reserva.setMontoReserva(dtoUpdateReserva.montoReserva());
        reserva.setActivo(true);

        reserva = reservaRepository.save(reserva);

        return mapToDto(reserva);
    }

    @Override
    public void deleteReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new QueryException("Reserva no encontrada"));

        reserva.setActivo(false);
        reservaRepository.save(reserva);
    }

    private DtoReservaResponse mapToDto(Reserva reserva) {
        return new DtoReservaResponse(
                reserva.getId(),
                reserva.getMesaId(),
                reserva.getCliente(),
                reserva.getFechaHora(),
                reserva.getMontoReserva(),
                reserva.isActivo()
        );
    }
}
