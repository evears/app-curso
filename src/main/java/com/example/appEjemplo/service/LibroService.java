package com.example.appEjemplo.service;

import com.example.appEjemplo.model.Libro;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.appEjemplo.repository.ILibroRepository;

@Service
@Transactional
public class LibroService {
    
    @Autowired
    ILibroRepository librorepo;

    public List<Libro> list() {
        return librorepo.findAll();
    }

    public Optional<Libro> getById(int id) {
        return librorepo.findById(id);
    }

    public Optional<Libro> getByNombre(String nombre) {
        return librorepo.findByNombre(nombre);
    }
    
    public Optional<Libro> getByAutor(String autor) {
        return librorepo.findByAutor(autor);
    }
    
    public void save(Libro prod) {
        librorepo.save(prod);
    }

    public void delete(int id) {
        librorepo.deleteById(id);
    }

    public boolean existsById(int id) {
        return librorepo.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return librorepo.existsByNombre(nombre);
    }
    
}
