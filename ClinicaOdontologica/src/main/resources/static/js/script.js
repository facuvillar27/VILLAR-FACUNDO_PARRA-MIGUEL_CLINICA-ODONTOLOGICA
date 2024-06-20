const apiUrl = "http://localhost:8080";

// Pacientes
function listarPacientes() {
  fetch(`${apiUrl}/pacientes/listar`)
    .then((response) => response.json())
    .then((data) => {
      const lista = document.getElementById("pacientes-lista");
      lista.innerHTML = "";
      data.forEach((paciente) => {
        lista.innerHTML += `
          <p><b>Nombre</b>: ${paciente.nombre}</p> 
          <p><b>Apellido</b>: ${paciente.apellido}</p> 
          <p><b>DNI</b>: ${paciente.dni}</p> 
          <p><b>Fecha de ingreso</b>: ${paciente.fechaIngreso}</p>
          <p><b>Domicilio</b>:
          ${paciente.domicilioSalidaDto.calle} - 
          ${paciente.domicilioSalidaDto.numero} - 
          ${paciente.domicilioSalidaDto.localidad} - 
          ${paciente.domicilioSalidaDto.provincia}
          </p>
          <p>________________________</p>`;
      });
    });
}

function registrarPaciente(event) {
  event.preventDefault();

  const nombre = document.getElementById("paciente-nombre").value;
  const apellido = document.getElementById("paciente-apellido").value;
  const dni = document.getElementById("paciente-dni").value;
  const fechaIngreso = document.getElementById("paciente-fechaIngreso").value;
  const calle = document.getElementById("paciente-calle").value;
  const numero = document.getElementById("paciente-numero").value;
  const localidad = document.getElementById("paciente-localidad").value;
  const provincia = document.getElementById("paciente-provincia").value;

  const dataJson = {
    nombre: nombre,
    apellido: apellido,
    dni: dni,
    fechaIngreso: fechaIngreso,
    domicilioEntradaDto: {
      calle: calle,
      numero: numero,
      localidad: localidad,
      provincia: provincia,
    },
  };

  fetch(`${apiUrl}/pacientes/registrar`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(dataJson),
  })
    .then((response) => response.json())
    .then((data) => {
      alert("Paciente registrado");
      listarPacientes();
    });
}

function eliminarPaciente(event) {
  const id = document.getElementById("paciente-id").value;

  const url = `${apiUrl}/pacientes/eliminar?id=${id}`;

  const options = {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
  };

  fetch(url, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error en la solicitud: " + response.statusText);
      }
      return response.json();
    })
    .then((data) => {
      console.log("Éxito:", data);
    })
    .catch((error) => {
      console.error("Error:", error);
    });
}
