package com.backend.ClinicaOdontologica;

import com.backend.ClinicaOdontologica.dbconnection.H2Connection;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	public static void main(String[] args) {

		H2Connection.crearTablas();
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
