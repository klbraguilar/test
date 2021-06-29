package com.test.example.controller;

import com.test.example.dto.Mensaje;
import com.test.example.entity.Paciente;
import com.test.example.service.PaceinteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.test.example.controller.DoctorController.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/paciente"})
public class PacienteController {
    @Autowired
    PaceinteService pacienteService;

    @GetMapping("/lista")
    public ResponseEntity<List<Paciente>> lista(){
        List<Paciente> listaPacientes = pacienteService.listar();
        return new ResponseEntity(listaPacientes, STATUS_OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getById(@PathVariable("id") int id){
        if (noExistePaciente(id))
            return new ResponseEntity(new Mensaje(NO_EXISTE), NOT_FOUND);
        Paciente paciente = pacienteService.listarPorID(id).get();
        return new ResponseEntity(paciente, STATUS_OK);
    }

    private boolean noExistePaciente(@PathVariable("id") int id) {
        return !pacienteService.existsbyId(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPaciente(@RequestBody Paciente paciente){
        pacienteService.save(paciente);
        return new ResponseEntity(new Mensaje(CREADO_CORRECTAMENTE), STATUS_OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable("id") int id, @RequestBody Paciente paciente){
        paciente.setId(id);
        pacienteService.save(paciente);
        return new ResponseEntity(new Mensaje(ACTUALIZADO_CORRECTAMENTE), STATUS_OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if (noExistePaciente(id))
            return new ResponseEntity(new Mensaje(NO_EXISTE), NOT_FOUND);
        pacienteService.delete(id);
        return new ResponseEntity(new Mensaje(ELIMINADO_CORRECTAMENTE), STATUS_OK);
    }

}
