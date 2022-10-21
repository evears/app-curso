package com.example.appEjemplo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "productos")
@Getter
@Setter
public class Producto {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    
    private double precio;
    
    private int cantUnid;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "marca_id")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Marca marca;

    
    public Producto() {
    }

    public Producto(String nombre, double precio, int cantUnid, Marca marca) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantUnid = cantUnid;
        this.marca = marca;
    }

    
    
}
