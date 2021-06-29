package com.test.example.repository;

import com.test.example.entity.Consulta;
import com.test.example.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
    Optional<Consulta> findById(int id);
}
