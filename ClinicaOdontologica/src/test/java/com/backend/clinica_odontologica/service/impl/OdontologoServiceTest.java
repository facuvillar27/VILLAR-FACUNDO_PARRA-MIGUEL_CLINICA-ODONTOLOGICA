package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void deberiaRegistrarseUnOdontologoDeNombrePepito_yRetornarSuId() {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("123456", "Pepito", "Perez");

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        // assert
        assertNotNull(odontologoSalidaDto);
        assertNotNull(odontologoSalidaDto.getId());
        assertEquals("Pepito", odontologoSalidaDto.getNombre());
    }

    @Test
    @Order(2)
    public void deberiaEliminarseElOdontologoConId1() {
        assertDoesNotThrow(() -> odontologoService.eliminarOdontologo(1L));
    }

    @Test
    @Order(3)
    public void deberiaDevolverUnaListaVaciaDeOdontologos() {
        List<OdontologoSalidaDto> listadoDeOdontologos = odontologoService.listarOdontologos();
        assertFalse(!listadoDeOdontologos.isEmpty());
    }



}