package com.example.appEjemplo.service;

import com.example.appEjemplo.model.Producto;
import com.example.appEjemplo.repository.IProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductoService {
    
    @Autowired
    IProductoRepository produrepo;

    public List<Producto> list() {
        return produrepo.findAll();
    }

    public Optional<Producto> getById(int id) {
        return produrepo.findById(id);
    }

    public Optional<Producto> getByNombre(String nombre) {
        return produrepo.findByNombre(nombre);
    }
    
    public void save(Producto prod) {
        produrepo.save(prod);
    }

    public void delete(int id) {
        produrepo.deleteById(id);
    }

    public boolean existsById(int id) {
        return produrepo.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return produrepo.existsByNombre(nombre);
    }
    
    
  
    
}
