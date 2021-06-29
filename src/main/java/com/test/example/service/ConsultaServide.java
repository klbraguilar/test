package com.test.example.service;

import com.test.example.entity.Consulta;
import com.test.example.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConsultaServide {
    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> listar() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> listarPorID(int id) {
        return consultaRepository.findById(id);
    }

    public Consulta save(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public void delete(int id) {
        consultaRepository.deleteById(id);
    }

    public boolean existsbyId(int id){
        return consultaRepository.existsById(id);
    }

}
