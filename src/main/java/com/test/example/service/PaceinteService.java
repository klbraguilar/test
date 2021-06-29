package com.test.example.service;

import com.test.example.entity.Paciente;
import com.test.example.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaceinteService {

    @Autowired
    private PacienteRepository pacienteRepository;


    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> listarPorID(int id) {
        return pacienteRepository.findById(id);
    }

    public Optional<Paciente> obtenerPorNombre(String nombre){
        return pacienteRepository.findByNombre(nombre);
    }

    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void delete(int id) {
        pacienteRepository.deleteById(id);
    }

    public boolean existsbyId(int id){
        return pacienteRepository.existsById(id);
    }

}
