package com.hospital_vm.cl.hospital_vm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.hospital_vm.cl.hospital_vm.model.Atencion;
import com.hospital_vm.cl.hospital_vm.service.AtencionService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/atenciones")
public class AtencionController {

    @Autowired
    private AtencionService atencionService;

    @GetMapping
    public ResponseEntity<List<Atencion>> listarAtenciones() {
        List<Atencion> atenciones = atencionService.getAllAtenciones();
        return ResponseEntity.ok(atenciones);
    }

    @GetMapping("/pacientes/{idPaciente}")
    public ResponseEntity<List<Atencion>> obtenerAtencionesPorPaciente(@PathVariable Long idPaciente) {
        List<Atencion> atenciones = atencionService.getAtencionesByPacienteId(idPaciente);

        if (atenciones == null || atenciones.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atenciones);
    }

    @PostMapping
    public ResponseEntity<Atencion> crearAtencion(@RequestBody Atencion atencion) {
        Atencion nuevaAtencion = atencionService.createAtencion(atencion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAtencion);
    }

    @PostMapping("/pacientes/{pacienteId}")
    public ResponseEntity<Atencion> crearAtencion(@PathVariable Long pacienteId, @RequestBody Atencion atencion) {
        Atencion nuevaAtencion = atencionService.createAtencionByPacienteID(atencion, pacienteId);
        if (nuevaAtencion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAtencion);
    }
}
