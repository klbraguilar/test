package com.test.example.controller;

import com.test.example.dto.Mensaje;
import com.test.example.entity.Doctor;
import com.test.example.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/doctores"})
public class DoctorController {
    public static final String CREADO_CORRECTAMENTE = "Creado correctamente";
    public static final String NO_EXISTE = "No existe";
    public static final HttpStatus STATUS_OK = HttpStatus.OK;
    public static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;
    public static final String ACTUALIZADO_CORRECTAMENTE = "Actualizado Correctamente";
    public static final String ELIMINADO_CORRECTAMENTE = "Eliminado...";
    @Autowired
    DoctorService doctorService;

    @GetMapping("/lista")
    public ResponseEntity<List<Doctor>> list(){
        List<Doctor> listaDoctores = doctorService.listar();
        return new ResponseEntity(listaDoctores, STATUS_OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getById(@PathVariable("id") int id){
        if (noExisteDoctor(id))
            return new ResponseEntity(new Mensaje(NO_EXISTE), NOT_FOUND);
        Doctor doctor = doctorService.listarPorID(id).get();
        return new ResponseEntity(doctor, STATUS_OK);
    }

    private boolean noExisteDoctor(@PathVariable("id") int id) {
        return !doctorService.existsbyId(id);
    }

    @GetMapping("/doctor/{nombre}")
    public ResponseEntity<Doctor> getByNombre(@PathVariable("nombre") String nombre){
        if (!doctorService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje(NO_EXISTE), NOT_FOUND);
        Doctor doctor = doctorService.obtenerPorNombre(nombre).get();
        return new ResponseEntity(doctor, STATUS_OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDoctor(@RequestBody Doctor doctor){
        doctorService.save(doctor);
        return new ResponseEntity(new Mensaje(CREADO_CORRECTAMENTE), STATUS_OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable("id") int id, @RequestBody Doctor doctor){
        doctor.setId(id);
        doctorService.save(doctor);
        return new ResponseEntity(new Mensaje(ACTUALIZADO_CORRECTAMENTE), STATUS_OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if (noExisteDoctor(id))
            return new ResponseEntity(new Mensaje(NO_EXISTE), NOT_FOUND);
        doctorService.delete(id);
        return new ResponseEntity(new Mensaje(ELIMINADO_CORRECTAMENTE), STATUS_OK);
    }
}
