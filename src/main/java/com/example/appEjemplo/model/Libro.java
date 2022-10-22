package com.example.appEjemplo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "libros")
@Getter
@Setter
public class Libro {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "autor")
    private String autor;
    
    @Column(name = "editorial")
    private String editorial;
    
    @Column(name = "anio")
    private int anio;
    
    @Column(name = "fueLeido")
    private boolean fueLeido;
    
    @Column(name = "formato")
    @Enumerated(EnumType.STRING)
    private Formato formato;

    public Libro() {
    }

    public Libro(String nombre, String autor, String editorial, int anio, boolean fueLeido, Formato formato) {
        this.nombre = nombre;
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
        this.fueLeido = fueLeido;
        this.formato = formato;
    }
    
    
}
