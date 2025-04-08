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
        try {
            Reserva reserva = new Reserva();
            reserva.setMesaId(dtoSaveReserva.mesaId());
            reserva.setCliente(dtoSaveReserva.cliente());
            reserva.setFechaHora(dtoSaveReserva.fechaHora());
            reserva.setMontoReserva(dtoSaveReserva.montoReserva());
            reserva.setActivo(true);

            return mapToDto(reservaRepository.save(reserva));
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public List<DtoReservaResponse> getAllReservas() {
        try {
            return reservaRepository.findByActivoTrue()
                    .stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public DtoReservaResponse getReservaById(Long id) {
        try {
            var reserva = reservaRepository.findById(id)
                    .orElseThrow(() -> new QueryException("Reserva no encontrada"));

            return mapToDto(reserva);
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public DtoReservaResponse updateReserva(Long id, DtoUpdateReserva dtoUpdateReserva) {
        try {
            var reserva = reservaRepository.findById(id)
                    .orElseThrow(() -> new QueryException("Reserva no encontrada"));

            if (dtoUpdateReserva.mesaId() != null) {
                reserva.setMesaId(dtoUpdateReserva.mesaId());
            }

            if (dtoUpdateReserva.cliente() != null && !dtoUpdateReserva.cliente().isBlank()) {
                reserva.setCliente(dtoUpdateReserva.cliente());
            }

            if (dtoUpdateReserva.fechaHora() != null) {
                reserva.setFechaHora(dtoUpdateReserva.fechaHora());
            }

            if (dtoUpdateReserva.montoReserva() != null) {
                reserva.setMontoReserva(dtoUpdateReserva.montoReserva());
            }

            reserva.setActivo(true);
            return mapToDto(reservaRepository.save(reserva));
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public void deleteReserva(Long id) {
        try {
            var reserva = reservaRepository.findById(id)
                    .orElseThrow(() -> new QueryException("Reserva no encontrada"));

            reserva.setActivo(false);
            reservaRepository.save(reserva);
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }
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
