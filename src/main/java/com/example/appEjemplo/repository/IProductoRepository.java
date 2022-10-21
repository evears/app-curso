package com.example.appEjemplo.repository;

import com.example.appEjemplo.model.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {
    public Optional<Producto> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
