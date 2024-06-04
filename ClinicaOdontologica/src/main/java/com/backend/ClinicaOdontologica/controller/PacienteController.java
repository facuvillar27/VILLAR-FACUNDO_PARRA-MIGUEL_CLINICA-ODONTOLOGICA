package com.backend.ClinicaOdontologica.controller;

import com.backend.ClinicaOdontologica.dto.entrada.PacienteEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.PacienteSalidaDto;
import com.backend.ClinicaOdontologica.service.IPacienteService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    //peticion http en json --> @RequestBody & @ResponseBody --> java dto --> controller dto --> servicio dto --> entidad --> persistencia entidad <--> base de datos

    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/registrar")
    public PacienteSalidaDto registrarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto) {
        return pacienteService.registrarPaciente(pacienteEntradaDto);
    }

    @GetMapping("/listar")
    public List<PacienteSalidaDto> listarPacientes(){
        return pacienteService.listarPacientes();
    }


}
