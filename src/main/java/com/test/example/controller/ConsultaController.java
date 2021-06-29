package com.test.example.controller;

import com.test.example.dto.Mensaje;
import com.test.example.entity.Consulta;
import com.test.example.entity.Doctor;
import com.test.example.entity.Paciente;
import com.test.example.service.ConsultaServide;
import com.test.example.service.DoctorService;
import com.test.example.service.PaceinteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.test.example.controller.DoctorController.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/consulta"})
public class ConsultaController {
    @Autowired
    ConsultaServide consultaServide;
    @Autowired
    PaceinteService pacienteService;
    @Autowired
    DoctorService doctorService;

    @GetMapping("/lista")
    public ResponseEntity<List<Consulta>> list(){
        List<Consulta> listaDoctores = consultaServide.listar();
        return new ResponseEntity(listaDoctores, STATUS_OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> getById(@PathVariable("id") int id){
        if (noExisteConsulta(id))
            return new ResponseEntity(new Mensaje(NO_EXISTE), NOT_FOUND);
        Consulta consulta = consultaServide.listarPorID(id).get();
        return new ResponseEntity(consulta, STATUS_OK);
    }

    private boolean noExisteConsulta(@PathVariable("id") int id) {
        return !consultaServide.existsbyId(id);
    }

    @PostMapping(value = "/cons/{pacId}/{docId}")
    public ResponseEntity<?> agregar(@PathVariable (value = "pacId") int id, @PathVariable (value = "docId") int idDoc
            ,@RequestBody Consulta consulta){
        AsignarDatosConsulta(id, idDoc, consulta);
        consultaServide.save(consulta);
        return new ResponseEntity(new Mensaje(CREADO_CORRECTAMENTE), STATUS_OK);
    }

    private void AsignarDatosConsulta(int id, int idDoc, Consulta consulta) {
        Paciente paciente = pacienteService.listarPorID(id).get();
        Doctor doctorId = doctorService.listarPorID(idDoc).get();
        consulta.setPaciente(paciente);
        consulta.setDoctor(doctorId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable("id") int id, @RequestBody Consulta consulta){
        consulta.setId(id);
        consultaServide.save(consulta);
        return new ResponseEntity(new Mensaje(ACTUALIZADO_CORRECTAMENTE), STATUS_OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if (noExisteConsulta(id))
            return new ResponseEntity(new Mensaje(NO_EXISTE), NOT_FOUND);
        consultaServide.delete(id);
        return new ResponseEntity(new Mensaje(ELIMINADO_CORRECTAMENTE), STATUS_OK);
    }
}
