package com.test.example.service;

import com.test.example.entity.Doctor;
import com.test.example.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> listar() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> listarPorID(int id) {
        return doctorRepository.findById(id);
    }

    public Optional<Doctor> obtenerPorNombre(String nombre){
        return doctorRepository.findByNombre(nombre);
    }

    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void delete(int id) {
        doctorRepository.deleteById(id);
    }

    public boolean existsbyId(int id){
        return doctorRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return doctorRepository.existsByNombre(nombre);
    }

}
