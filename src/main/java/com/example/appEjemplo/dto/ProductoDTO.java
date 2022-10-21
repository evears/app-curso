package com.example.appEjemplo.dto;

import com.example.appEjemplo.model.Marca;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTO {
    
    @NotBlank
    private int id;
    
    @NotBlank
    private String nombre;
    
    @NotBlank
    private double precio;
    
    @NotBlank
    private int cantUnid;
    
    @NotBlank
    private Marca marca;
    
    
    public ProductoDTO() {
    }

    public ProductoDTO(String nombre, double precio, int cantUnid, Marca marca) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantUnid = cantUnid;
        this.marca = marca;
    }

    
   
    
}
