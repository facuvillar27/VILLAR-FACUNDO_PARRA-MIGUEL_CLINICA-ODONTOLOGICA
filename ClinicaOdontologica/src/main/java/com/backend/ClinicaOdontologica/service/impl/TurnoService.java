package com.backend.ClinicaOdontologica.service.impl;

import com.backend.ClinicaOdontologica.dto.entrada.TurnoEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.TurnoSalidaDto;
import com.backend.ClinicaOdontologica.entity.Odontologo;
import com.backend.ClinicaOdontologica.entity.Paciente;
import com.backend.ClinicaOdontologica.entity.Turno;
import com.backend.ClinicaOdontologica.repository.IDao;
import com.backend.ClinicaOdontologica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private IDao<Turno> turnoIDao;
    private final ModelMapper modelMapper;

    public TurnoService(IDao<Turno> turnoIDao, ModelMapper modelMapper) {
        this.turnoIDao = turnoIDao;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) {
        // logica de negocio
        // mapeo de dto a entidad
        LOGGER.info("TurnoEntradaDto: " + turnoEntradaDto);
        Turno turno = modelMapper.map(turnoEntradaDto, Turno.class);
        LOGGER.info("TurnoEntidad: " + turno);
        // mapeo de entidad a dto
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoIDao.registrar(turno), TurnoSalidaDto.class);
        LOGGER.info("TurnoSalidaDto: " + turnoSalidaDto);
        return turnoSalidaDto;
    }

    private void configureMapping(){
        modelMapper.typeMap(TurnoEntradaDto.class, Turno.class)
                .addMappings(mapper -> {
                    mapper.using(ctx -> modelMapper.map(((TurnoEntradaDto) ctx.getSource()).getOdontologoEntradaDto(), Odontologo.class))
                            .map(TurnoEntradaDto::getOdontologoEntradaDto, Turno::setOdontologo);
                    mapper.using(ctx -> modelMapper.map(((TurnoEntradaDto) ctx.getSource()).getPacienteEntradaDto(), Paciente.class))
                            .map(TurnoEntradaDto::getPacienteEntradaDto, Turno::setPaciente);
                });

        modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
                .addMappings(mapper -> {
                mapper.map(src -> src.getOdontologo().getNombre(), TurnoSalidaDto::setNombreOdontologo);
                mapper.map(src -> src.getOdontologo().getApellido(), TurnoSalidaDto::setApellidoOdontologo);
                mapper.map(src -> src.getPaciente().getNombre(), TurnoSalidaDto::setNombrePaciente);
                mapper.map(src -> src.getPaciente().getApellido(), TurnoSalidaDto::setApellidoPaciente);
                });
    }

}
