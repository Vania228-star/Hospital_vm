package com.hospital_vm.cl.hospital_vm.repository;

import com.hospital_vm.cl.hospital_vm.model.Paciente;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    //Encuentra pacientes por apellidos
    List<Paciente> findByApellidos (String Apellidos);

    //Encuentra pacientes por correo electronico
    Paciente findByCorreo (String Correo);

    //Encuentra pacientes por nombre y apellido
    List<Paciente> findByNombreAndApellidos (String Nombre, String Apellidos);
}
