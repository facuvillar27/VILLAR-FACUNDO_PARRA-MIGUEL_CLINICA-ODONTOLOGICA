package com.backend.ClinicaOdontologica.dto.entrada;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class OdontologoEntradaDto {

    @Positive(message = "El número no puede ser nulo o menor a cero")
    @Digits(integer = 8, fraction = 0, message = "El número debe tener como máximo 8 digitos")
    private int numeroMatricula;

    @NotBlank(message = "Debe proveerse el nombre del odontologo")
    private String nombre;

    @NotBlank(message = "Debe proveerse el apellido del odontologo")
    private String apellido;

    public OdontologoEntradaDto() {
    }

    public OdontologoEntradaDto(int numeroMatricula, String nombre, String apellido) {
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
