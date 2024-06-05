package com.backend.ClinicaOdontologica.repository.impl;

import com.backend.ClinicaOdontologica.dbconnection.H2Connection;
import com.backend.ClinicaOdontologica.entity.Turno;
import com.backend.ClinicaOdontologica.repository.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurnoDaoH2 implements IDao<Turno> {
    private final Logger LOGGER = LoggerFactory.getLogger(TurnoDaoH2.class);

    @Override
    public Turno registrar(Turno turno) {
        Connection connection = null;
        Turno turnoRegistrado = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO TURNOS (FECHA_Y_HORA, ODONTOLOGO_ID, PACIENTE_ID) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(turno.getFechaYHora()));
            preparedStatement.setLong(2, turno.getOdontologo().getId());
            preparedStatement.setLong(3, turno.getPaciente().getId());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            turnoRegistrado = new Turno(resultSet.getLong("id"), turno.getFechaYHora(), turno.getOdontologo(), turno.getPaciente());

            while (resultSet.next()) {
                turnoRegistrado.setId(resultSet.getLong("id"));
            }

            connection.commit();
            LOGGER.info("Se ha registrado el turno: " + turnoRegistrado);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection!= null) {
                try {
                    connection.rollback();
                    LOGGER.info("Tuvimos un problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }

        return turnoRegistrado;
    }

    @Override
    public Turno buscarPorId(Long id) {
        // Implementación similar a los otros métodos buscarPorId
        return null;
    }

    @Override
    public List<Turno> listarTodos() {
        // Implementación similar a los otros métodos listarTodos
        return null;
    }
}
