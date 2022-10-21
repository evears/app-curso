package com.example.appEjemplo.repository;

import com.example.appEjemplo.model.Marca;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMarcaRepository extends JpaRepository<Marca, Integer> {
    
    public Optional<Marca> findByNombre(String nombre);
    
    public boolean existsByNombre(String nombre);
    
}
