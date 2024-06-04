package com.backend.ClinicaOdontologica.dto.salida;

import com.backend.ClinicaOdontologica.entity.Odontologo;
import com.backend.ClinicaOdontologica.entity.Paciente;

import java.time.LocalDateTime;

public class TurnoSalidaDto {
    //mostrar solo id, fechayhora, odontologo(nombre y apellido), paciente(nombre y apellido)
    private Long id;

    private LocalDateTime fechaYHora;

    private Odontologo odontologo;

    private Paciente paciente;
}
