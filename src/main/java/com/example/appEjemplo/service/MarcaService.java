package com.example.appEjemplo.service;

import com.example.appEjemplo.model.Marca;
import com.example.appEjemplo.repository.IMarcaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MarcaService {
    
    @Autowired
    IMarcaRepository marcarepo;

    public List<Marca> list() {
        return marcarepo.findAll();
    }

    public Optional<Marca> getById(int id) {
        return marcarepo.findById(id);
    }

    public Optional<Marca> getByNombre(String nombre) {
        return marcarepo.findByNombre(nombre);
    }

    public void save(Marca marca) {
        marcarepo.save(marca);
    }

    public void delete(int id) {
        marcarepo.deleteById(id);
    }

    public boolean existsById(int id) {
        return marcarepo.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return marcarepo.existsByNombre(nombre);
    }
    



}
