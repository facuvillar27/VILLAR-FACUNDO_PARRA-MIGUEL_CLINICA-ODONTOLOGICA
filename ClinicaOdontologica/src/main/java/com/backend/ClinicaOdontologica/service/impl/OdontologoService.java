package com.backend.ClinicaOdontologica.service.impl;

import com.backend.ClinicaOdontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.OdontologoSalidaDto;
import com.backend.ClinicaOdontologica.entity.Odontologo;
import com.backend.ClinicaOdontologica.repository.IDao;
import com.backend.ClinicaOdontologica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OdontologoService implements IOdontologoService {

    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private IDao<Odontologo> odontologoIDao;
    private final ModelMapper modelMapper;

    public OdontologoService(IDao<Odontologo> odontologoIDao, ModelMapper modelMapper) {
        this.odontologoIDao = odontologoIDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {
        LOGGER.info("OdontologoEntradaDto: " + odontologoEntradaDto);
        Odontologo odontologo = modelMapper.map(odontologoEntradaDto, Odontologo.class);
        LOGGER.info("OdontologoEntidad: " + odontologo);
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoIDao.registrar(odontologo), OdontologoSalidaDto.class);
        LOGGER.info("OdontologoSalidaDto: " + odontologoSalidaDto);
        return odontologoSalidaDto;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        List<OdontologoSalidaDto> odontologos = odontologoIDao.listarTodos()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class))
                .toList();
        LOGGER.info("Listado de todos los odontologos: {}", odontologos);

        return odontologos;
    }
}
