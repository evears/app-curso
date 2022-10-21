package com.example.appEjemplo.controller;

import com.example.appEjemplo.dto.MarcaDTO;
import com.example.appEjemplo.model.Marca;
import com.example.appEjemplo.service.MarcaService;
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
public class MarcaController {
    
    @Autowired
    private MarcaService marcaserv;

    
    @GetMapping("/marca/vertodas")
    public ResponseEntity<List<Marca>> list() {
        List<Marca> list = marcaserv.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/marca/ver/{id}")
    public ResponseEntity<Marca> getById(@PathVariable("id") int id) {
        if (!marcaserv.existsById(id)) {
            return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        }
        Marca marca = marcaserv.getById(id).get();
        return new ResponseEntity(marca, HttpStatus.OK);
    }

    @DeleteMapping("/marca/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!marcaserv.existsById(id)) {
            return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        }
        marcaserv.delete(id);
        return new ResponseEntity("se eliminó correctamente", HttpStatus.OK);
    }

    @PostMapping("/marca/crear")
    public ResponseEntity<?> create(@RequestBody MarcaDTO dtomarca) {
        if (StringUtils.isBlank(dtomarca.getNombre())) {
            return new ResponseEntity("El nombre de la marca es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (marcaserv.existsByNombre(dtomarca.getNombre())) {
            return new ResponseEntity("Esa marca ya existe", HttpStatus.BAD_REQUEST);
        }

        Marca marca = new Marca(dtomarca.getNombre());
        marcaserv.save(marca);

        return new ResponseEntity("Marca agregada", HttpStatus.OK);
    }

    @PutMapping("/marca/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody MarcaDTO dtomarca) {

        if (!marcaserv.existsById(id)) {
            return new ResponseEntity("El ID no existe", HttpStatus.BAD_REQUEST);
        }

        if (marcaserv.existsByNombre(dtomarca.getNombre()) && marcaserv.getByNombre(dtomarca.getNombre()).get().getId() != id) {
            return new ResponseEntity("La marca ya existe", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtomarca.getNombre())) {
            return new ResponseEntity("El nombre de la marca es obligatorio", HttpStatus.BAD_REQUEST);
        }

        Marca marca = marcaserv.getById(id).get();
        marca.setNombre(dtomarca.getNombre());

        marcaserv.save(marca);
        return new ResponseEntity("Marca actualizada", HttpStatus.OK);

    }
    
}
