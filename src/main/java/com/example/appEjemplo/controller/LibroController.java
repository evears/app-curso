package com.example.appEjemplo.controller;

import com.example.appEjemplo.dto.LibroDTO;
import com.example.appEjemplo.model.Libro;
import com.example.appEjemplo.service.LibroService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class LibroController {

    @Autowired
    private LibroService libroserv;

    @GetMapping("/libro/vertodos")
    public ResponseEntity<List<Libro>> list() {
        List<Libro> list = libroserv.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/libro/ver/{id}")
    public ResponseEntity<Libro> getById(@PathVariable("id") int id) {
        if (!libroserv.existsById(id)) {
            return new ResponseEntity("el libro indicado no existe", HttpStatus.NOT_FOUND);
        }
        Libro libro = libroserv.getById(id).get();
        return new ResponseEntity(libro, HttpStatus.OK);
    }

    @DeleteMapping("/libro/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!libroserv.existsById(id)) {
            return new ResponseEntity("el libro que se desea eliminar no existe", HttpStatus.NOT_FOUND);
        }
        libroserv.delete(id);
        return new ResponseEntity("se eliminó correctamente", HttpStatus.OK);
    }

    @PostMapping("/libro/crear")
    public ResponseEntity<?> create(@RequestBody LibroDTO dtolibro) {
        if (StringUtils.isBlank(dtolibro.getNombre())) {
            return new ResponseEntity("El nombre del libro es obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        if (libroserv.existsByNombre(dtolibro.getNombre())) {
            return new ResponseEntity("Es libro que desea agregar ya existe", HttpStatus.BAD_REQUEST);
        }

        Libro libro = new Libro(dtolibro.getNombre(), dtolibro.getAutor(), dtolibro.getEditorial(), 
                                dtolibro.getAnio(), dtolibro.isFueLeido(), dtolibro.getFormato());
        libroserv.save(libro);

        return new ResponseEntity("Libro agregado", HttpStatus.OK);
    }

    @PutMapping("/producto/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody LibroDTO dtolibro) {

        if (!libroserv.existsById(id)) {
            return new ResponseEntity("El ID no existe", HttpStatus.BAD_REQUEST);
        }

        if (libroserv.existsByNombre(dtolibro.getNombre()) && libroserv.getByNombre(dtolibro.getNombre()).get().getId() != id) {
            return new ResponseEntity("El libro ya existe", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtolibro.getNombre())) {
            return new ResponseEntity("El nombre del libro es obligatorio", HttpStatus.BAD_REQUEST);
        }

        Libro libro = libroserv.getById(id).get();
        libro.setNombre(dtolibro.getNombre());
        libro.setAutor(dtolibro.getAutor());
        libro.setEditorial(dtolibro.getEditorial());
        libro.setAnio(dtolibro.getAnio());
        libro.setFueLeido(dtolibro.isFueLeido());
        libro.setFormato(dtolibro.getFormato());

        libroserv.save(libro);
        return new ResponseEntity("Libro actualizado", HttpStatus.OK);

    }

}
