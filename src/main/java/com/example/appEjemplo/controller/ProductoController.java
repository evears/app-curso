package com.example.appEjemplo.controller;

import com.example.appEjemplo.dto.ProductoDTO;
import com.example.appEjemplo.model.Producto;
import com.example.appEjemplo.service.ProductoService;
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
public class ProductoController {

    @Autowired
    private ProductoService pserv;

    @GetMapping("/producto/vertodos")
    public ResponseEntity<List<Producto>> list() {
        List<Producto> list = pserv.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }


    @GetMapping("/producto/ver/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") int id) {
        if (!pserv.existsById(id)) {
            return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        }
        Producto prod = pserv.getById(id).get();
        return new ResponseEntity(prod, HttpStatus.OK);
    }

    @DeleteMapping("/producto/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!pserv.existsById(id)) {
            return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        }
        pserv.delete(id);
        return new ResponseEntity("se eliminó correctamente", HttpStatus.OK);
    }

    @PostMapping("/producto/crear")
    public ResponseEntity<?> create(@RequestBody ProductoDTO dtoprodu) {
        if (StringUtils.isBlank(dtoprodu.getNombre())) {
            return new ResponseEntity("El nombre del producto es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (pserv.existsByNombre(dtoprodu.getNombre())) {
            return new ResponseEntity("Ese producto ya existe", HttpStatus.BAD_REQUEST);
        }

        Producto produ = new Producto(dtoprodu.getNombre(), dtoprodu.getPrecio(), dtoprodu.getCantUnid(), dtoprodu.getMarca());
        pserv.save(produ);

        return new ResponseEntity("Producto agregado", HttpStatus.OK);
    }

    @PutMapping("/producto/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProductoDTO dtoprodu) {

        if (!pserv.existsById(id)) {
            return new ResponseEntity("El ID no existe", HttpStatus.BAD_REQUEST);
        }

        if (pserv.existsByNombre(dtoprodu.getNombre()) && pserv.getByNombre(dtoprodu.getNombre()).get().getId() != id) {
            return new ResponseEntity("El producto ya existe", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoprodu.getNombre())) {
            return new ResponseEntity("El nombre del producto es obligatorio", HttpStatus.BAD_REQUEST);
        }

        Producto prod = pserv.getById(id).get();
        prod.setNombre(dtoprodu.getNombre());
        prod.setPrecio(dtoprodu.getPrecio());
        prod.setCantUnid(dtoprodu.getCantUnid());

        pserv.save(prod);
        return new ResponseEntity("Producto actualizado", HttpStatus.OK);

    }

}
