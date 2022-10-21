package com.example.appEjemplo.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "marcas")
@Getter
@Setter
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
    private Set<Producto> productos = new HashSet<>();

    public Marca() {
    }

    public Marca(String nombre) {
        this.nombre = nombre;
    }

    public Marca(int id) {
        this.id = id;
    }
    
    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
        for (Producto producto : productos) {
            producto.setMarca(this);
        }
    }
    


}
