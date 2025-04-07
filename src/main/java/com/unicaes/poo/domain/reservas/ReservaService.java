package com.unicaes.poo.domain.reservas;

import com.unicaes.poo.domain.reservas.dto.DtoSaveReserva;
import com.unicaes.poo.domain.reservas.dto.DtoUpdateReserva;
import com.unicaes.poo.domain.reservas.dto.DtoReservaResponse;
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
        reserva.setMesaId(dtoSaveReserva.getMesaId());
        reserva.setCliente(dtoSaveReserva.getCliente());
        reserva.setFechaHora(dtoSaveReserva.getFechaHora());
        reserva.setMontoReserva(dtoSaveReserva.getMontoReserva());
        reserva = reservaRepository.save(reserva);
        return new DtoReservaResponse(reserva.getId(), reserva.getMesaId(), reserva.getCliente(), reserva.getFechaHora(), reserva.getMontoReserva());
    }

    @Override
    public List<DtoReservaResponse> getAllReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream()
                .map(reserva -> new DtoReservaResponse(reserva.getId(), reserva.getMesaId(), reserva.getCliente(), reserva.getFechaHora(), reserva.getMontoReserva()))
                .collect(Collectors.toList());
    }

    @Override
    public DtoReservaResponse getReservaById(Long id) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        return new DtoReservaResponse(reserva.getId(), reserva.getMesaId(), reserva.getCliente(), reserva.getFechaHora(), reserva.getMontoReserva());
    }

    @Override
    public DtoReservaResponse updateReserva(Long id, DtoUpdateReserva dtoUpdateReserva) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reserva.setMesaId(dtoUpdateReserva.getMesaId());
        reserva.setCliente(dtoUpdateReserva.getCliente());
        reserva.setFechaHora(dtoUpdateReserva.getFechaHora());
        reserva.setMontoReserva(dtoUpdateReserva.getMontoReserva());
        reserva = reservaRepository.save(reserva);
        return new DtoReservaResponse(reserva.getId(), reserva.getMesaId(), reserva.getCliente(), reserva.getFechaHora(), reserva.getMontoReserva());
    }

    @Override
    public void deleteReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reservaRepository.delete(reserva);
    }
}
